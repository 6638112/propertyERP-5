package test.com.cnfantasia.server.ms.payBill;

import java.util.ArrayList;
import java.util.List;

public class PayBillTest {
	public static void main(String[] args) {
		
		List<Integer> list = new ArrayList<Integer>();
		for(int i=0; i<201; i++){
			list.add(i);
		}
		
		int batchStepSize = 100; //一次插入100条
		System.out.println(list.size()/batchStepSize);
		
		for (int i = 0; i < list.size() / batchStepSize + 1; i++) {
			int endIndex = (i + 1) * batchStepSize > list.size() ? list.size() : (i + 1) * batchStepSize;
			System.err.println(list.subList(i * batchStepSize, endIndex));
		}
		
	}
}
