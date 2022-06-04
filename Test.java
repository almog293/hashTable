import java.util.*;

public class Test {

    public static void main(String[] args) {
        Q3();
    }
    public static void Qq1(){
        HashSet<Integer> hs = new HashSet<Integer>();
        int i = 0;
        int q = 6571;
        while ( i < q){
            int num = (((int) Math.pow(-1 , i)) * (i*i))%q;
            hs.add(num);
            i++;
        }
        System.out.println(hs.size());
    }
    public static void Q1(){
        int fullCounterAQ = 0;
        int existCounterAQ = 0;
        int fullCounterQP = 0;
        int existCounterQP = 0;
        for(int k = 0; k < 100; k++){
            QPHashTable qp = new QPHashTable(6571 , 10000019);
            AQPHashTable ap = new AQPHashTable(6571 , 10000019);
            Random random = new Random();
            for (int i = 0 ; i < 6571; i++){
                int b = random.nextInt(100);
                long a = 100*i + b;
                HashTableElement ht = new HashTableElement(a, a);
                try {
                    qp.Insert(ht);
                }
                catch (IHashTable.TableIsFullException | IHashTable.KeyAlreadyExistsException e){
                    if(e instanceof IHashTable.KeyAlreadyExistsException){
                        existCounterQP++;
                    }
                    else{
                        fullCounterQP++;
                    }
                }
                try{
                    ap.Insert(ht);
                }
                catch (IHashTable.TableIsFullException | IHashTable.KeyAlreadyExistsException e){
                    if(e instanceof IHashTable.KeyAlreadyExistsException){
                        existCounterAQ++;
                    }
                    else{
                        fullCounterAQ++;
                    }
                }

            }

        }
        System.out.println("Question1:" + "\n" + "QPHashTable:" + "\n" + "Full Exeption:" + fullCounterQP + " Exist Exeption:" + existCounterQP);
        System.out.println("AQHashTable:" + "\n" + "Full Exeption:" + fullCounterAQ + " Exist Exeption:" + existCounterAQ);
    }
    public static void Q2(){
        OAHashTable[] tables = new OAHashTable[4];
        int m = 10000019;
        int p = 1000000007;
        LPHashTable lp = new LPHashTable(m , p);
        QPHashTable qp = new QPHashTable(m , p);
        AQPHashTable ap = new AQPHashTable(m , p);
        DoubleHashTable dp = new DoubleHashTable(m , p);
        tables[0] = lp; tables[1] = qp; tables[2]  = ap; tables[3] = dp;
        int n = m/2;
        long[] number = new long[n];
        Random random = new Random();
        for(int i = 0; i < n; i++){
            int b = random.nextInt(100);
            long a = 100*i + b;
            number[i]= a;
        }
        for(OAHashTable table : tables){
            long start = System.currentTimeMillis();
            int counter = 0;
            for(int i = 0; i < n; i++){
                try{
                    table.Insert(new HashTableElement(number[i] , number[i]));
                }
                catch (IHashTable.TableIsFullException | IHashTable.KeyAlreadyExistsException e){
                    counter++;
                }

            }
            long end = System.currentTimeMillis();
            System.out.println(table.getClass() + ":" + "\n" + "Running Time:" + (end-start) + " Exception:" + counter);
        }

    }
    public static void Q3(){
        int m = 10000019;
        int p = 1000000007;
        DoubleHashTable dp = new DoubleHashTable(m,p);
        int n = m/2;



        for(int k = 0 ; k < 6; k++){
            int existCounter = 0;
            int fullCounter = 0;
            int dontExistCounter = 0;
            int[] number = new int[n];
            Random random = new Random();
            for(int i = 0; i < n; i++){
                number[i] = random.nextInt(100);;
            }
            long start = System.currentTimeMillis();
            for(int i = 0; i < n; i++){
                HashTableElement ht = new HashTableElement(100 *i + number[i] , i);
                try{
                    dp.Insert(ht);
                } catch (IHashTable.TableIsFullException e) {
                    fullCounter++;
                } catch (IHashTable.KeyAlreadyExistsException e) {
                    existCounter++;
                }
            }
            for(int i = 0; i < n; i++){
                try {
                    dp.Delete( 100 *i + number[i]);
                } catch (IHashTable.KeyDoesntExistException e) {
                    dontExistCounter++;
                }

            }
            long end = System.currentTimeMillis();
            System.out.println("i:" + k + " Time:" + (end-start));
            System.out.println("Full counter:" + fullCounter + " exist counter = " + existCounter + " not exist counter:" + dontExistCounter);
        }
    }
}
