public class test11213 {
	//此地無銀三百兩
    public static void main(String[] args) {
    	//二維陣列				位置===>0    1    2    3    4    5    6    7
        String AA[][]=new String [][]{{"此","無","此","太","銀","地","無","地"},
        							  {"無","地","者","銀","地","銀"},
        							  {"三","百","三","銀"},
        							  {"百","三","此","百","兩","兩"}};
        for(String ROW[]:AA){//利用foreach從AA二維陣列依序提取1維陣列至ROW
        	int num=0;//設定值
        	for(String COL:ROW){//利用foreach從ROW一維陣列依序提取值至COL
        		if((num==0)||(num==5)){//因為陣列裡第0個跟第5個是我們要的文字
        			System.out.print(COL+" ");//輸出
        		}
        		num++;//每跑一個就加一次
        	}
        }
    }
}
