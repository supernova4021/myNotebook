
public class CanReviveObj {
	public static CanReviveObj obj;

	// 该方法只会被调用一次
	@Override
	protected void finalize() throws Throwable {
		System.out.println("finalizing CanReviveObj....");
		super.finalize();
		obj = this;
	}
	
	public static void main(String[] args) throws Exception{
		obj = new CanReviveObj();
		obj = null;  //可复活
		System.out.println("第一次GC");
		System.gc();
		Thread.sleep(1000);
		System.out.println("obj is "+obj);
		System.out.println("第二次GC");
		obj = null;
		System.gc();
		Thread.sleep(1000);
		System.out.println("obj is "+obj);
	}
	
}

