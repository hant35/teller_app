package vn.fpt.dbp.vccb.service.system.configuration;

import java.util.Arrays;

import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.SimpleCommandBus;
import org.axonframework.commandhandling.annotation.AnnotationCommandHandlerBeanPostProcessor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.commandhandling.gateway.CommandGatewayFactoryBean;
import org.axonframework.commandhandling.interceptors.BeanValidationInterceptor;
import org.axonframework.eventhandling.ClusteringEventBus;
import org.axonframework.eventhandling.DefaultClusterSelector;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventhandling.EventBusTerminal;
import org.axonframework.eventhandling.SimpleCluster;
import org.axonframework.eventhandling.amqp.AMQPConsumerConfiguration;
import org.axonframework.eventhandling.amqp.DefaultAMQPMessageConverter;
import org.axonframework.eventhandling.amqp.spring.ListenerContainerLifecycleManager;
import org.axonframework.eventhandling.amqp.spring.SpringAMQPConsumerConfiguration;
import org.axonframework.eventhandling.amqp.spring.SpringAMQPTerminal;
import org.axonframework.eventhandling.annotation.AnnotationEventListenerBeanPostProcessor;
import org.axonframework.serializer.Serializer;
import org.axonframework.serializer.json.JacksonSerializer;
import org.axonframework.serializer.xml.XStreamSerializer;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.transaction.RabbitTransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.support.TaskUtils;

import vn.fpt.util.json.NoDeduplicateIdDeserializationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.deser.BeanDeserializerFactory;

/**
 * Axon Java Configuration with reasonable defaults like SimpleCommandBus, SimpleEventBus and GenericJpaRepository.
 * @author albert
 */
@Configuration
public class AxonConfiguration {
	// Rabbit
//    private @Value("${rabbitmq.host}") String rabbitHost;
//    private @Value("${rabbitmq.port}") Integer rabbitPort;
//    private @Value("${rabbitmq.username}") String rabbitUsername;
//    private @Value("${rabbitmq.password}") String rabbitPassword;
//    private @Value("${rabbitmq.exchange.name}") String rabbitExchangeName;
//    private @Value("${rabbitmq.exchange.autodelete}") boolean rabbitExchangeAutodelete;
//    private @Value("${rabbitmq.exchange.durable}") boolean rabbitExchangeDurable;
//    private @Value("${rabbitmq.queue.name}") String rabbitQueueName;
//    private @Value("${rabbitmq.queue.durable}") boolean rabbitQueueDurable;
//    private @Value("${rabbitmq.queue.exclusive}") boolean rabbitQueueExclusive;
//    private @Value("${rabbitmq.queue.autodelete}") boolean rabbitQueueAutoDelete;
//    private @Value("${rabbitmq.queue-listener.prefetch-count}") int rabbitQueueListenerPrefetchCount;
//    private @Value("${rabbitmq.queue-listener.recovery-interval}") long rabbitQueueListenerRecoveryInterval;
//    private @Value("${rabbitmq.queue-listener.cluster-transaction-size}") int rabbitQueueClusterTransactionSize;	
//    private @Value("${rabbitmq.concurent.consumers}") int rabbitConcurentConsumers;
    
    private String rabbitHost = "10.86.202.223";//"192.168.99.100";
    private int rabbitPort = 5672;
    private String rabbitUsername = "vccb";
    private String rabbitPassword = "F15.B4nk";
    private String rabbitExchangeName = "tellerapp.exchange";
    private boolean rabbitExchangeAutodelete = false;
    private boolean rabbitExchangeDurable = true;
    private String rabbitQueueName ="tellerapp.system_queue";
    private boolean rabbitQueueDurable = true;
    private boolean rabbitQueueExclusive = false;
    private boolean rabbitQueueAutoDelete = false;
    private int rabbitQueueListenerPrefetchCount = 100;
    private long rabbitQueueListenerRecoveryInterval = 1000;
    private int rabbitQueueClusterTransactionSize = 100;	
    private int rabbitConcurentConsumers = 10;

	
//	@Autowired
//	private PlatformTransactionManager transactionManager;
	
    
 // Serializer
//    @Bean
//    public XStreamSerializer xstreamSerializer() {
//        return new XStreamSerializer();
//    }

    @Bean
    public Serializer jacksonSerializer() {
    	NoDeduplicateIdDeserializationContext deserializationContext = new NoDeduplicateIdDeserializationContext(BeanDeserializerFactory.instance);
    	ObjectMapper mapper = new ObjectMapper(null, null, deserializationContext);
    	mapper.configure(SerializationFeature.USE_EQUALITY_FOR_OBJECT_ID, true);
    	return new JacksonSerializer(mapper);
    }
    // Connection Factory
    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(rabbitHost, rabbitPort);
        connectionFactory.setUsername(rabbitUsername);
        connectionFactory.setPassword(rabbitPassword);
        return connectionFactory;
    }


//    // Event bus exchange
//    @Bean
//    public FanoutExchange eventBusExchange() {
//    	//new DirectExchange(rabbitExchangeName, rabbitExchangeDurable, rabbitExchangeAutodelete);
//        return new FanoutExchange(rabbitExchangeName, rabbitExchangeDurable, rabbitExchangeAutodelete);
//    }
//
//
//    // Event bus queue
//    @Bean
//    public Queue eventBusQueue() {
//    	System.out.println("=========rabbitQueueExclusive="+rabbitQueueExclusive);
//        return new Queue(rabbitQueueName, rabbitQueueDurable, rabbitQueueExclusive, rabbitQueueAutoDelete);
//    }
//
//
//    // Binding
//    @Bean
//    public Binding binding() {
//        return BindingBuilder.bind(eventBusQueue()).to(eventBusExchange());
//    }


    // Rabit Admin
    @Bean
    public RabbitAdmin rabbitAdmin() {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory());
        rabbitAdmin.setAutoStartup(true);
        return rabbitAdmin;
    }
    
    //Axon
	@Bean
	public AnnotationEventListenerBeanPostProcessor annotationEventListenerBeanPostProcessor() {
		AnnotationEventListenerBeanPostProcessor processor = new AnnotationEventListenerBeanPostProcessor();
		processor.setEventBus(eventBus());
		return processor;
	}
	
	@Bean
	public AnnotationCommandHandlerBeanPostProcessor annotationCommandHandlerBeanPostProcessor() {
		AnnotationCommandHandlerBeanPostProcessor processor = new AnnotationCommandHandlerBeanPostProcessor();
		processor.setCommandBus(commandBus());
		return processor;
	}

	@Bean
	public CommandBus commandBus() {
		SimpleCommandBus commandBus = new SimpleCommandBus();
		commandBus.setHandlerInterceptors(Arrays.asList(new BeanValidationInterceptor()));
//		commandBus.setTransactionManager(new SpringTransactionManager(transactionManager));
		return commandBus;
	}

	
//	@Bean
//	public EventBus eventBus() {
//		return new SimpleEventBus();
//	}

	// Event bus
    @Bean
    public EventBus eventBus() {
        ClusteringEventBus clusteringEventBus = new ClusteringEventBus(new DefaultClusterSelector(simpleCluster()), terminal());
        return clusteringEventBus;
    }


    // Terminal
    @Bean
    public EventBusTerminal terminal() {
        SpringAMQPTerminal terminal = new SpringAMQPTerminal();
        terminal.setConnectionFactory(connectionFactory());
        terminal.setSerializer(jacksonSerializer());
        terminal.setExchangeName(rabbitExchangeName);
        terminal.setListenerContainerLifecycleManager(listenerContainerLifecycleManager());
        terminal.setDurable(true);
        terminal.setTransactional(false);
        return terminal;
    }


    // Configuration
    @Bean
    AMQPConsumerConfiguration springAMQPConsumerConfiguration() {
        SpringAMQPConsumerConfiguration springAMQPConsumerConfiguration = new SpringAMQPConsumerConfiguration();
        springAMQPConsumerConfiguration.setDefaults(null);
        springAMQPConsumerConfiguration.setQueueName(rabbitQueueName);
        springAMQPConsumerConfiguration.setErrorHandler(TaskUtils.getDefaultErrorHandler(false));
        springAMQPConsumerConfiguration.setAcknowledgeMode(AcknowledgeMode.NONE);
        springAMQPConsumerConfiguration.setConcurrentConsumers(rabbitConcurentConsumers);
        springAMQPConsumerConfiguration.setRecoveryInterval(rabbitQueueListenerRecoveryInterval);
        springAMQPConsumerConfiguration.setExclusive(false);
        springAMQPConsumerConfiguration.setPrefetchCount(rabbitQueueListenerPrefetchCount);
        springAMQPConsumerConfiguration.setTransactionManager(new RabbitTransactionManager(connectionFactory()));
        springAMQPConsumerConfiguration.setTxSize(rabbitQueueClusterTransactionSize);
        return springAMQPConsumerConfiguration;
    }


    // Cluster definition
    @Bean
    SimpleCluster simpleCluster() {
        SimpleCluster simpleCluster = new SimpleCluster(rabbitQueueName);
        return simpleCluster;
    }


    // Message converter
    @Bean
    DefaultAMQPMessageConverter defaultAMQPMessageConverter() {
        return new DefaultAMQPMessageConverter(jacksonSerializer());
    }	
    // Message listener configuration
    @Bean
    ListenerContainerLifecycleManager listenerContainerLifecycleManager() {
        ListenerContainerLifecycleManager listenerContainerLifecycleManager = new ListenerContainerLifecycleManager();
        listenerContainerLifecycleManager.setConnectionFactory(connectionFactory());
        return listenerContainerLifecycleManager;
    }

    
	@Bean
	public CommandGatewayFactoryBean<CommandGateway> commandGatewayFactoryBean() {
		CommandGatewayFactoryBean<CommandGateway> factory = new CommandGatewayFactoryBean<CommandGateway>();
		factory.setCommandBus(commandBus());
		return factory;
	}

//	@Bean
//	public EntityManagerProvider entityManagerProvider() {
//		return new ContainerManagedEntityManagerProvider();
//	}
	
	/*
	@Bean
	public EventSourcingRepository<User> userEventSourcingRepository() {
		FileSystemEventStore eventStore = new FileSystemEventStore(new SimpleEventFileResolver(new File("data/evenstore")));
		EventSourcingRepository<User> repository = new EventSourcingRepository<User>(User.class, eventStore);
		repository.setEventBus(eventBus());
		return repository;
	}
	
	@Bean
	public AggregateAnnotationCommandHandler<User> cifCommandHandler() {
		AggregateAnnotationCommandHandler<User> commandHandler = AggregateAnnotationCommandHandler.subscribe(User.class, userEventSourcingRepository(), commandBus());
		return commandHandler;
	}
	*/
}
