/**
* @Package PACKAGE_NAME
* @author xiaolanhe
* @date 2022/3/18 18:17
* @version V1.0
*//**
 *@author: xiaolanhe
 *@createDate: 2022/3/18 18:17
*/
public class KMP {

    public static int getIndexOf(String source, String target)
    {
        if(source == null || target == null || target.length() < 1 || source.length() < target.length())
        {
            return -1;
        }

        char[] str = source.toCharArray();
        char[] match = target.toCharArray();

        // 当前匹配的位置
        int x = 0;
        int y = 0;

        int[] next = getNextArray(match);
        while(x < str.length && y < match.length)
        {
            if(str[x] == match[y]){
                x++;
                y++;
            }else if(next[y] == -1){ // str中当前的字符在match中已经无法继续匹配
                x++;
            }else{
                //match当前的字符无法继续匹配，回到前面子串的最大前后缀相等位置的下一个位置继续匹配
                y = next[y];
            }
        }

        return y == match.length ? x-y : -1;
    }

    private static int[] getNextArray(char[] match) {

        int length = match.length;

        if(length == 1) return new int[]{-1};

        int[] next = new int[length];

        next[0] = -1;
        next[1] = 0;

        int i = 2; // 当前在哪个位置上求next数组的值

        int cur = 0; // 当前是哪个位置上的值在和 i-1 位置上的值进行比较

        while(i < length) {

            // 当前位置 i 的前一个位置 i-1 的字符 如果与这个字符对应的下标索引 j，在match中next[j]索引位置的字符相等，那这个位置的next元素为cur+1
            if (match[i - 1] == match[cur]) {
                next[i] = cur+1;
                cur++;
                i++;
            }else if(cur > 0)
            {
                cur = next[cur]; // 不断的找上个位置，若回退到字符串开头 ，则代表没有前后缀公共长度为0
            }else{
                next[i++] = 0;
            }
        }

        return next;
    }
}
