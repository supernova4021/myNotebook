// handwriting.... two way quick sort

void qsort(vector<int> arr){
	qsort(arr,0,arr.size());
}

void qsort(vector<int> arr, int low, int high){
	if(low >= high){
		return;
	}
	int pivot = partition(arr,low,high);
	qsort(arr, low, pivot-1);
	qsort(arr, pivot+1, high);
}

int partition(vector<int> arr, int low, int high){
	int p = arr[low];
	while(low < high){
		while(low < high && p <= arr[high]){
			high--;
		}
		arr[low]=arr[high];
		while(low < high && p >= arr[low]){
			low++;
		}
		arr[high]=arr[low];
	}
	arr[low]=p;
	return low;
}

// three way quick sort
public static void qsort(int[] a){  
	//为获得接近于最好的排序性能，先打乱再排序  
	shuffle(a);  
	qsort(a,0,a.length - 1);  
}  
  
/** 
 * 三向切分的快速排序实现 
 * @param a 
 * @param lo 
 * @param hi 
 */  
private static void qsort(int[] a,int lo,int hi){ 
	if(lo < hi){
		int lt = lo;
		int i = lo + 1;
		int gt = hi;
		int pivot = a[lo];
		while(i <= gt){
			if(a[i] < pivot){
				swap(a, lt++, i++);
			}else if(a[i] > pivot){
				//这里i不能--，因为交换后i的位置还未确定
				//上面的lt可以++是因为lt指向的是还未确定的
				swap(a, i, gt--);
			}else{
				i++;
			}
		}
		qsort(a, lo, lt - 1);
		qsort(a, gt + 1, hi);
	}
}
