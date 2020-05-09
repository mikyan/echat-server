package cn.mikyan;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import cn.mikyan.netty.WSServer;

/*
 * 集成ApplicationListener可以在启动时完成一些任务
 * 要完成的任务重写在onApplicationEvent里
 * 观察者模式
 */
@Component
public class NettyBooter implements ApplicationListener<ContextRefreshedEvent> {

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		/*
		 * 只执行一边，在其他容器初试化时不操作，只在父容器初试化时操作
		 */
		if (event.getApplicationContext().getParent() == null) {
			try {
				WSServer.getInstance().start();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}
