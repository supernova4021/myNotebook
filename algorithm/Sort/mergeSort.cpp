// handwriting....

void msort(vector<int> arr){
	msort(arr,0,arr.size());
}

void msort(vector<int> arr, int low, int high){
	if(low >= high){
		return;
	}
	int mid = (low+high)/2;
	msort(arr, low, mid);
	msort(arr, mid+1, high);
	merge(arr, low, mid, high);
}

void merge(vector<int> arr, int low, int mid, int high){
	int left = low;
	int right = mid+1;
	int i = low;
	vector<int> t_arr = vector<int>(high-low+1);
	while(left <= mid && right <= high){
		t_arr[i++] = arr[left]<=arr[right]?arr[left++]:arr[right++]
	}
	while(left <= mid){
		t_arr[i++] = arr[left++];
	}
	while(right <= high){
		t_arr[i++] = arr[right++];
	}
	for(int k=low; k<=high; k++){
		arr[k] = t_arr[k];
	}
}