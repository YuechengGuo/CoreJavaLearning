package Assignment1;

/*
 * Create a Singleton class using different approaches 
 */

public class Q16 {
	public static class Singleton {
		
		private static Singleton singleton = new Singleton();

		private Singleton(){} // prevents other class from instantiating

		public Singleton getInstance() {
			return singleton;
		}

		protected void demo() {
			// description for the class
		}
	}

	public static class Singleton1 {
		private static Singleton1 singleton1 = null;

		private Singleton1(){} // prevents other class from instantiating

		public Singleton1 getInstance() {
			if (singleton1 == null) {
				singleton1 = new Singleton1();
			}
			return singleton1;
		}

		protected void demo() {
			// description for the class
		}
	}

	public static void main(String[] args) {
		Singleton s = new Singleton();
		Singleton stest1 = s.getInstance();
		// Singleton s1 = new Singleton();
		Singleton stest2 = s.getInstance();
		System.out.println(stest1 == stest2);
	}
}
