package com.firstProject.springBatch.config;

import com.firstProject.springBatch.dao.StudentRepo;
import com.firstProject.springBatch.domain.Student;
import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@EnableBatchProcessing
@AllArgsConstructor
public class BatchConfig {

    public JobBuilderFactory jobBuilderFactory;

    public StepBuilderFactory stepBuilderFactory;

    public StudentRepo studentRepo;

    @Bean
    public FlatFileItemReader<Student> reader() {
        return new FlatFileItemReaderBuilder<Student>()
                .name("csvDataReader")
                .resource(new ClassPathResource("student.csv"))
                .linesToSkip(1)
                .delimited()
                .names("first","last","GPA","AGE")
                .fieldSetMapper(new BeanWrapperFieldSetMapper<Student>() {{
                    setTargetType(Student.class);
                }})
                .build();
    }

    @Bean
    public StudentProcessor processor() {
        return new StudentProcessor();
    }

    @Bean
    public RepositoryItemWriter<Student> writer() {
                RepositoryItemWriter<Student> writer =  new RepositoryItemWriter<>();
                writer.setRepository(studentRepo);
                writer.setMethodName("save");
                return writer;
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1").<Student, Student> chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }
    @Bean
    public Job runJob(){
        return jobBuilderFactory.get("import student").flow(step1())
                .end().build();
    }
}

