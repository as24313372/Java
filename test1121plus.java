import java.util.Scanner;//import Scanner�Ψӱ��y�ϥΪ̿�J

public class test1121plus {
    public static void main(String[] args) {
        Scanner data = new Scanner(System.in);//�W�[�@��scanner�Ŷ��W��data
        String num[] = data.nextLine().split(" ");//���y�ϥΪ̥������@��åB�ΪŮ���Ψ�num�}�C��
        switch(num.length){//�P�_list�̦��X�Ӥ���
        	case 1:		   //���Q��num.length�Ӫ��D�ϥΪ̦@���F�X�ӡA�b�P�_�O�n�]����overload
        		juice(num[0]);
        		break;
        	case 2:
        		juice(num[0],num[1]);
        		break;
        	case 3:
        		juice(num[0],num[1],num[2]);
        		break;
        }
    }
    //�Ƶ{����OVERLOAD�Ӷ]
    static void juice(String s1){
    	System.out.print("�ڭn�@�M"+s1+"juice");
    }
    static void juice(String s1,String s2){
    	System.out.print("�ڭn"+s1+"�M"+s2+"juice");
    }
    static void juice(String s1,String s2,String s3){
    	System.out.print("�ڭn"+s1+"�M"+s2+"��"+s3+"juice");
    }
}
