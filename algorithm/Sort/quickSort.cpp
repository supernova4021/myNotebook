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