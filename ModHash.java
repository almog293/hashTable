import java.util.Random;

public class ModHash {
	long a;
	long b;
	long m;
	long p;

	public ModHash(long a , long b , int m , long p){
		this.a = a;
		this.b = b;
		this.p = p;
		this.m = m;
	}

	public static ModHash GetFunc(int m, long p){
		Random random = new Random();
		long a = random.nextInt(m);
		long b = random.nextInt(m);
		return new ModHash(a,b ,m ,p);
	}
	
	public int Hash(long key) {
		return (int) ( ((this.a*key + this.b)%this.p)%this.m);
	}
}
