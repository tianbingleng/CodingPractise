/**
 * Created by tianbingleng on 21/6/2017.
 */
public class Fibonacci {
    static public void main(String[] args) {
        /*
        DP的​核心思想​类似于我们高中学习的数学归纳法：

        1.把一个大问题(size​ ​==​ ​n)的解决方案用比他小的问题（问题们）来解决，也就是思考从问
        题size​ ​=​ ​n-1​ ​增加到​ ​size​ ​=​ ​n​ ​的时候,​ ​如何用小问题的solution构建大问题的solution。

        2.与recursion的关系：
            2.1.Recursion​ ​从"大到小"来解决问题，不记录任何sub-solution只要考虑
                2.1.1.base​ ​case
                2.1.2.recursive​ ​rule

            2.2.DP​ ​从"小到大"来解决问题，记录sub-solution
                2.2.1.由size​ ​(<​ ​n)​ ​的​ ​subsolution(​s​)​ ​​ ​​ ​→​ ​size​ ​(n)​ ​​ ​的solution
                2.2.2.base​ ​case
                2.2.3.Induction​ ​rule
        */

        System.out.println(fibonacci(0));
        System.out.println(fibonacci(1));
        System.out.println(fibonacci(2));
        System.out.println(fibonacci(3));
        System.out.println(fibonacci(4));
        System.out.println(fibonacci(5));
        System.out.println(fibonacci(6));
        System.out.println(fibonacci(7));
        System.out.println(fibonacci(8));
        System.out.println(fibonacci(9));

    }
    static public long fibonacci(int K) {
        // Write your solution here
        if (K <= 0) {
            return 0;
        }
        long[] DPArray = new long[K + 1];
        DPArray[0] = 0;
        DPArray[1] = 1;
        for (int i = 2; i <= K; i++) {
            DPArray[i] = DPArray[i - 1] + DPArray[i - 2];
            //​ ​​ ​fib[2]​ ​= ​ f[0]​ ​+​ ​f[1]
            //​ ​​ ​fib[3]​ ​=​ ​​ ​f[1]​ ​+​ ​f[2]
        }
        return DPArray[K];
    }
}
