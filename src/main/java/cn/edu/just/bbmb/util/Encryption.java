package cn.edu.just.bbmb.util;
public class Encryption {
    public static String encryptAndDencrypt(String value, char secret)
    {
        byte[] bt=value.getBytes(); //将需要加密的内容转换为字节数组
        for(int i=0;i<bt.length;i++)
        {
            bt[i]=(byte)(bt[i]^(int)secret); //通过异或运算进行加密
        }
        String newresult=new String(bt,0,bt.length); //将加密后的字符串保存到 newresult 变量中
        return newresult;
    }
}
