public class test11212 {
    public static void main(String[] args) {
        int [] B = new int [6];//�إߤ@�Ӱ}�C�W��B�A�B�̭��u��6�Ӥ���
        int sum=0;//��l�ȥΨӪ���`�X
        for(int i=0;i<6;i++){//����6�Ӷü�
        	B[i]=(int)(Math.random()*6)+1;//���ͶüƦb�s�JB lsit��i�Ӧ�m
        	sum+=B[i];//�[���`�X
        	System.out.print("��"+(i+1)+"�ӼƬO"+B[i]+"\n");//��X��i�쪺�üƭ�
        }
        System.out.print("�`�M="+sum+"\n");//��X�`�X
        for(int i=1;i<7;i++){//�P�_B list�̬O�_�����ƶW�L1���H�W���ü�
        	int count=0;//�O��
        	for(int j=0;j<6;j++){//�q1-6�P�_����
        		if(B[j]==i)//�p�G�}�C�̦��۲Ū���count�N�[�@
        			count++;
        	}
        	if(count>1)//�P�_1�ƬO�_�W�L1�ӥH�W
        		System.out.print("�Ʀr"+i+"����"+count+"��");
        }
    }
}
