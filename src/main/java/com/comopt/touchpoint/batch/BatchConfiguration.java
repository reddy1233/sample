package com.comopt.touchpoint.batch;

import java.util.Date;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.MapJobRepositoryFactoryBean;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.comopt.touchpoint.AppConstant;
import com.comopt.touchpoint.model.TouchPointActor;

@Configuration
@EnableBatchProcessing
@EnableScheduling
public class BatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;
   
    @Bean
    public CustomItemReader reader() {
    	
    	System.out.println("reader.....");
    	
     return new CustomItemReader();
    }
    
    @Bean
    public TPAItemProcessor processor() {
        return new TPAItemProcessor();
    }
    
    @Bean
    public Step step1() {
    	  return stepBuilderFactory.get("chunkStep")
    		        .<TouchPointActor, TouchPointActor>chunk(1)
    		        .reader(reader())
    		        .processor(processor())
    		        .writer(writer())
    		        .build();
    }
    
    @Bean
    public Job testJob() {
     return jobBuilderFactory.get("testJob")
       .incrementer(new RunIdIncrementer())
       .flow(step1())
       .end()
       .build();
    }

   

    @Bean
    public CustomItemWriter writer() {
    	System.out.println("writer.....");
     return new CustomItemWriter();
    }
   

 /** Job launcher and scheduler integration code..**/
    
    @Autowired
    private SimpleJobLauncher jobLauncher;
    
    @Bean
    public SimpleJobLauncher jobLauncher(JobRepository jobRepository) {
        SimpleJobLauncher launcher = new SimpleJobLauncher();
        launcher.setJobRepository(jobRepository);
        return launcher;
    }
    
   // @Scheduled(cron = "*/50 * * * * *")
    @Scheduled(fixedRate = 60000)
  	public void perform() throws Exception {

  		System.out.println("Job Started at :" + new Date());
  		
  		AppConstant.isReadComplete = false;

  		JobParameters param = new JobParametersBuilder().addString("JobID", String.valueOf(System.currentTimeMillis()))
  				.toJobParameters();

  		JobExecution execution = jobLauncher.run(testJob(), param);

  		System.out.println("Job finished with status :" + execution.getStatus());
  	}
}
