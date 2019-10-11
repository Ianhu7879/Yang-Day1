package saleTicket;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
//多线程总结: 线程 控制 资源类
//资源类
class Ticket{
	//方式一 synchronized 同步方法
	/*private int number = 30;
	public synchronized void sale(){
		if(number > 0){
			System.out.println(Thread.currentThread().getName() + "正在出售第" + (number--) + "票" + "还剩"+ number + "票");
		}
	}*/
	//方式二 锁机制
	private int number = 30;
	Lock lock = new ReentrantLock();
	public void sale(){
		lock.lock();
		try {
			if(number > 0){
				System.out.println(Thread.currentThread().getName() + "正在出售第" + (number--) + "票" + "还剩"+ number + "票");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
	}
	
	
} 

public class SaleTicket {
	public static void main(String[] args) {
		Ticket ticket = new Ticket();
		//lamda表达式总结: 拷贝小括号,写死右箭头,落地大括号
		new Thread(() -> {for(int i = 1 ; i < 40 ; i++) ticket.sale();}, "窗口1").start();
		new Thread(() -> {for(int i = 1 ; i < 40 ; i++) ticket.sale();}, "窗口2").start();
		new Thread(() -> {for(int i = 1 ; i < 40 ; i++) ticket.sale();}, "窗口3").start();
		
		/*new Thread(() -> {for (int i = 1; i < 30; i++) {
			ticket.sale();
		}}, "A").start();
		*/
		/*final Ticket ticket = new Ticket();
		new Thread(new Runnable() {
			public void run() {
				for(int i = 1 ; i < 40 ; i++){
					ticket.sale();
				}
			}
		}, "窗口1").start();
		new Thread(new Runnable() {
			
			public void run() {
				for(int i = 1 ; i < 40 ; i++){
					ticket.sale();
				}
			}
		}, "窗口2").start();
		new Thread(new Runnable() {
			
			public void run() {
				for(int i = 1 ; i < 40 ; i++){
					ticket.sale();
				}
			}
		}, "窗口3").start();
		*/
	}
}
