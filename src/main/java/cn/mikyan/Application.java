package cn.mikyan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import io.netty.bootstrap.ServerBootstrap;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * 在这里记点笔记，方便自己看也方便同组同学看
 * 
 *
 * 1.代码结构里，pojo，vo，bo都是怎么回事
 * 具体可以看我发在微信里的链接
 * https://www.cnblogs.com/EasonJim/p/7967949.html
 * 
 * 1.1 pojo
 * cn.mikyan.pojo下面主要是存实体类，他没什么方法
 * POJO有一些Private的参数作为对象的属性。
 * 然后针对每个参数定义了get和set方法作为访问的接口
 * Plain Ordinary Java Object简单Java对象
 * 即POJO是一个简单的普通的Java对象
 * 它不包含业务逻辑或持久逻辑等，但不是JavaBean、EntityBean等，
 * 不具有任何特殊角色和不继承或不实现任何其它Java框架的类或接口。
 * POJO对象有时也被称为Data对象，大量应用于表现现实中的对象。
 * 
 * 1.2vo
 * 业务逻辑层回传到表示层用的pojo
 * 1.3bo
 * 业务逻辑层接收表示层回传用的pojo
 * 
 * @author MIKYAN
 *
 */


/**
 * 课程里，老师在添加好友部分，使用了请求的方式，每次点进聊天列表就查一次，
 * 因为老师在聊天时才建立websocket
 * 链接，所以不能通过websocket链接来发送通知，这里我们的架构会更改，在登录时就建立
 * 好websocket链接，之后的好友提醒直接通过websocket链接发送
 *
 * @author MIKYAN
 *
 */

/**
 * 前端消息发送的逻辑有点问题，没联网还是会把发送的消息渲染在页面上，而且没有任何提示
 * 联网之后没有重发
 * @author MIKYAN
 *
 */
@SpringBootApplication

@MapperScan(basePackages="cn.mikyan.mapper")
//扫描组件，会把cn.mikyan.*包里的component加入到spring 容器里管理
@ComponentScan(basePackages= {"cn.mikyan","org.n3r.idworker"})
public class Application {

	@Bean
	public SpringUtil getSpingUtil() {
		return new SpringUtil();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
