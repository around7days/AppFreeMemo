// package rms;
//
// import org.apache.commons.lang3.builder.ToStringBuilder;
// import org.apache.commons.lang3.builder.ToStringStyle;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
// import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
// import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.CommandLineRunner;
// import org.springframework.boot.SpringApplication;
// import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.context.ApplicationContext;
//
/// **
// * SpringBatchApplicationクラス<br>
// * @author
// */
// @SpringBootApplication
// @EnableBatchProcessing
// public class SpringBatchApplication implements CommandLineRunner {
//
// /** logger */
// private static final Logger logger = LoggerFactory.getLogger(SpringBatchApplication.class);
//
// @Autowired
// private JobBuilderFactory jobs;
//
// @Autowired
// private StepBuilderFactory steps;
//
// // @Bean
// // protected Tasklet tasklet() {
// //
// // return new Tasklet() {
// // @Override
// // public RepeatStatus execute(StepContribution contribution,
// // ChunkContext context) {
// // return RepeatStatus.FINISHED;
// // }
// // };
// //
// // }
// //
// // @Bean
// // public Job job() throws Exception {
// // return this.jobs.get("job").start(step1()).build();
// // }
// //
// // @Bean
// // protected Step step1() throws Exception {
// // return this.steps.get("step1").tasklet(tasklet()).build();
// // }
//
// /**
// * バッチ起動
// * @param args
// */
// public static void main(String[] args) {
//
// // SpringApplication起動クラスの生成
// SpringApplication application = new SpringApplication(SpringBatchApplication.class);
// application.setWebEnvironment(false); // 内臓tomcatの起動を抑制
//
// // 処理実行
// logger.info("バッチ処理開始");
// logger.info("パラメータ：{}", ToStringBuilder.reflectionToString(args, ToStringStyle.SIMPLE_STYLE));
// ApplicationContext context = application.run(args);
// int ret = SpringApplication.exit(context);
// logger.info("終了コード：{}", ret);
// logger.info("バッチ処理終了");
//
// // 終了
// System.exit(ret);
// }
//
// /*
// * (非 Javadoc)
// * @see org.springframework.boot.CommandLineRunner#run(java.lang.String[])
// */
// @Override
// public void run(String... args) throws Exception {
// System.out.println("実行しました");
// }
//
// }
