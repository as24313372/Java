public class test11215 {
	/* 1�����ƬO��1�����Ƹ�@�����Ʃҥ�
	 * �@�����ƬO��1�����Ʃҥ�
	 *�ӥ��ƪ��W20�N��
	 *����=1,1,2,3,5,8,13,21.....
	 *����=0,1,1,2,3,5, 8,13.....
	 *�N��=0,1,2,3,4,5, 6, 7.....
	 *
	 */
    public static void main(String[] args) {
        System.out.println("����"+f1(20));
        System.out.println("����"+m1(20));
    }
    //�ѤW���W�߱o�X�Wn�N�����ƬO�Wn�N�����Ƹ�W(n-1)�N�����Ƭۥ[
    //�p�G�O��0�N�h�O1������
    public static int f1(int n){
    	if(n==0)return 1;
    	return m1(n)+m1(n-1);
    }
    //�Q�λ��j�ӹB��
    //�ѤW���W�߱o�X�Wn�N�����ƬO�Wn-1�N������
    //��0�N�O0������
    public static int m1(int n){
    	if(n==0)return 0;
    	return f1(n-1);
    }
}
