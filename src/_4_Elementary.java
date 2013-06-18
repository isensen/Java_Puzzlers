/**
 * Created with IntelliJ IDEA.
 * User: i33
 * Date: 13-6-18
 * Time: 下午5:19
 */
public class _4_Elementary {

    public static void main(String[] args){


        //将一个int类型的12345加到了long类型的5432l上. 结果 : 17777
        System.out.println( 12345 + 5432l );

        //教训：在long型字面常量中，一定要用大写的L，千万不要用小写的l。这样就可以完全掐断这个谜题所产生的混乱的源头。
        System.out.println(12345+5432L);

    }
}
