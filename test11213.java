public class test11213 {
	//���a�L�ȤT�ʨ�
    public static void main(String[] args) {
    	//�G���}�C				��m===>0    1    2    3    4    5    6    7
        String AA[][]=new String [][]{{"��","�L","��","��","��","�a","�L","�a"},
        							  {"�L","�a","��","��","�a","��"},
        							  {"�T","��","�T","��"},
        							  {"��","�T","��","��","��","��"}};
        for(String ROW[]:AA){//�Q��foreach�qAA�G���}�C�̧Ǵ���1���}�C��ROW
        	int num=0;//�]�w��
        	for(String COL:ROW){//�Q��foreach�qROW�@���}�C�̧Ǵ����Ȧ�COL
        		if((num==0)||(num==5)){//�]���}�C�̲�0�Ӹ��5�ӬO�ڭ̭n����r
        			System.out.print(COL+" ");//��X
        		}
        		num++;//�C�]�@�ӴN�[�@��
        	}
        }
    }
}
