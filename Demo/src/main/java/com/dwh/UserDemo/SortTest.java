package com.dwh.UserDemo;

import java.util.Random;

/**
 * ClassName:   SortTest <br/>
 * Description: TODO ADD DESCRIPTION. <br/>
 * date:        2016年7月28日 上午8:25:39 <br/>
 *
 * @author      danier
 */
public class SortTest {
    static int[] a = {5, 17, 16, 7, 10, 9, 18, 4, 15, 14, 3, 1, 19, 0, 20, 6, 13, 2, 12, 8, 11};

    /**
     * main: ADD DESCRIPTION. <br/>
     * 执行流程: (可选). <br/>
     * 使用方法: (可选). <br/>
     * 注意事项: (可选). <br/>
     *
     * @param args
     * @author danier
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        //long begin = System.currentTimeMillis();
        SortTest st = new SortTest();
       // st.bubbleSort(a);
        //st.bubbleSort_1(a);
       // st.selectSort(a);
        //st.insertSort(a);
        //st.quickSort(a,0,a.length-1);
      /*  for(int k:a){
            System.out.print(k);
            System.out.print(" ");
        }*/
        //st.BinaryInsertSort(a);
        Random ra = new Random(System.currentTimeMillis());
        int[] test = new int[30];
        for(int i = 0;i<test.length;i++){
            test[i] = ra.nextInt(100);
        }
        for(int k:test){
            System.out.print(k);
            System.out.print(" ");
        }
        st.radixSort(test);
        System.out.println("");
        for(int k:test){
            System.out.print(k);
            System.out.print(" ");
        }
    }

    public static void swap(int[] a,int i,int j){
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    //    复杂度分析：一共要比较 ((n-1)+(n-2)+...+3+2+1)=n*(n-1)/2次，所以时间复杂度是O(n^2)
    //    冒泡排序就是把小的元素往前调或者把大的元素往后调。比较是相邻的两个元素比较，
    //    交换也发生在这两个元素之间。所以，如果两个元素相等，我想你是不会再无聊地把他们俩交换一下的；
    //    如果两个相等的元素没有相邻，那么即使通过前面的两两交换把两个相邻起来，这时候也不会交换，
    //    所以相同元素的前后顺序并没有改变，所以冒泡排序是一种稳定排序算法。
    //    第一层循环代表第i个数据排好序，一共要使n-1个数排好即排序的趟数；第二层循环是进行两两对比交换的，所以起点一定是0
    public void bubbleSort(int[] a) {
        /*for (int i = a.length - 1; i > 1; i--) {
            for (int j = 0; j < i; j++) {
                if (a[j] > a[j + 1]) swap(j, j + 1);
            }
        }*/
        for(int i = a.length - 1; i > 1; i--){
            for(int j = 0; j < i; j++){
                if(a[j]>a[j+1]){
                    swap(a,j,j+1);
                }
            }
        }
    }

    /**
     * 冒泡排序的优化
     * 用一个数记录一趟排序中是否进行了交换
     */
    public void bubbleSort_1(int[] a) {
        boolean isChange = false;
        for(int i = a.length - 1; i > 1; i--){
            isChange = false;
            for(int j = 0; j < i; j++){
                if(a[j]>a[j+1]){
                    swap(a,j,j+1);
                    isChange = true;
                }
            }
            if (!isChange){
                break;
            }
        }
    }

    //    选择排序是不稳定算法，最好的情是已经排好顺序，只要比较n*(n-1)/2次即可，
    //    最坏情况是逆序排好的，那么还要移动O(n)次,由于是低阶故而不考虑不难得出选择排序的时间复杂度是O(n^2)
    //    比较拗口，举个例子，序列5 8 5 2 9， 我们知道第一遍选择第1个元素5会和2交换，那么原序列中2个5的相对前后顺序就被破坏了，所以选择排序不是一个稳定的排序算法
    public void selectSort(int[] a) {
        for(int i = 0; i<a.length ; i++){
            int min = i;
            for(int j = i;j<a.length;j++){
               if(a[min]>a[j]) min = j;
            }
            swap(a,i,min);
        }
    }

    //    插入排序的思想是这样的，第一层for循环表示要循环n次，且每次循环要操作的主体是a[i]，第二层循环是对
    //    a[i]的具体操作，是从原数祖第i个位置起，向前比较，所以插入排序的平均时间复杂度也是O(n^2).
    //    比较是从有序序列的末尾开始，也就是想要插入的元素和已经有序的最大者开始比起，
    //    如果比它大则直接插入在其后面，否则一直往前找直到找到它该插入的位置。如果碰见一个和插入元素相等的，
    //    那么插入元素把想插入的元素放在相等元素的后面。所以，相等元素的前后顺序没有改变，
    //    从原无序序列出去的顺序就是排好序后的顺序，所以插入排序是稳定的。
    void insertSort(int[] a) {
        for(int i =1;i<a.length;i++){
            int temp = a[i];
            int j = i;
            while(j>=1&&a[j-1]>temp){
                a[j] = a[j-1];
                j--;
            }
            a[j] = temp;
        }
    }

    /**
     * 二分插入排序
     * @param a 待排序数组
     */
    //    二分查找排序是稳定的，不会改变相同元素的相对顺序，
    //    1. 时间复杂度：O(n^2)
    //    二分查找插入位置，因为不是查找相等值，而是基于比较查插入合适的位置，所以必须查到最后一个元素才知道插入位置。
    //    二分查找最坏时间复杂度：当2^X>=n时，查询结束，所以查询的次数就为x，而x等于log2n（以2为底，n的对数）。即O(log2n)
    //    所以，二分查找排序比较次数为：x=log2n
    //    二分查找插入排序耗时的操作有：比较 + 后移赋值。时间复杂度如下：
    //    1)        最好情况：查找的位置是有序区的最后一位后面一位，则无须进行后移赋值操作，其比较次数为：log2n  。即O(log2n)
    //    2)        最坏情况：查找的位置是有序区的第一个位置，则需要的比较次数为：log2n，需要的赋值操作次数为n(n-1)/2加上 (n-1) 次。即O(n^2)
    //    3)        渐进时间复杂度（平均时间复杂度）：O(n^2)
    public void BinaryInsertSort(int[] a){
        int length = a.length;
        if(length==0) return;
        int start = 0, end = 0,mid = 0;
        int key=0;

        for(int i = 1;i<length;i++){
            key = a[i];
            start = 0;
            end = i-1;
            while(end>=start){
                mid = (start+end)/2;
                if(key<a[mid]){
                    end = mid-1;
                }
                else{
                    //如果相等，则保证新元素在旧元素后
                    start = mid+1;
                }
            }
            for(int j = i;j>start;j--){
                a[j] = a[j-1];
            }
            a[start] = key;
        }
    }

    /**
     * 快速排序
     */
    public void quickSort(int[] a,int start ,int end){
        int l = start;//左
        int r = end;//右
        int mid = a[(start+end)/2];
        //r==l的情况只能是r和l所指向的数据为mid，基准数
        //所以进入循环后在进行l++和r--后就是基准数左右的坐标
        while(r>=l){
            while (mid>a[l]) l++;
            while(mid<a[r]) r--;
            if(r>=l){
                swap(a,r,l);
                l++;
                r--;
            }
        }
        if(r>start)//已经能作为终止循环的条件了
        quickSort(a,start,r);
        if(l<end)
        quickSort(a,l,end);
    }


    //    1)        最好情况：序列是升序排列，在这种情况下，需要进行的比较操作需（n-1）次。后移赋值操作为0次。即O(n)
    //    2)        最坏情况：O(nlog2n)。
    //    3)        渐进时间复杂度（平均时间复杂度）：O(nlog2n)
    //    希尔排序是不稳定的。因为在进行分组时，相同元素可能分到不同组中，改变相同元素的相对顺序
    public void hillsort() {
        int h = 1;
        while (h < a.length / 3) {
            h = h * 3 + 1;
        }
        while (h > 0) {
            for (int i = 1; i < a.length; i++) {
                int temp = a[i], j = i;
                while (j > h - 1 && a[j - h] > temp) {
                    a[j] = a[j - h];
                    j -= h;
                }
                a[j] = temp;
            }
            h = (h - 1) / 3;
        }
    }

    /**
     * 归并排序
     * @param a 数组
     * @param L 左边界
     * @param R 右边界
     */

    //    平均时间复杂度O（nlogn），最坏时间复杂度O（n*n）,辅助空间O（logn）<每次都要分给一个额外空间，而总共有logn次>
//    每次分成两段，那么分的次数就是logn了，每一次处理需要n次计算，那么时间复杂度就是nlogn了！
//    根据平均情况来说是O(nlogn),因为在数据分布等概率的情况下对于单个数据来说在logn次移动后就会被放到正确的位置上了。
//    最坏是O(n^2).这种情况就是数组刚好的倒序，然后每次去中间元的时候都是取最大或者最小。
//    稳定性：不稳定。

    public void mergeSort(int[] a,int L,int R){
        if(L==R){
            return;
        }
        int mid = (L+R)/2;
        mergeSort(a,L,mid);
        mergeSort(a,mid+1,R);
        merge(a,L,mid+1,R);
    }

    /**
     *
     * @param a 元书纸
     * @param L 左边界
     * @param mid 第二个序列的第一个元素
     * @param R 右边界
     */
    public void merge(int[] a,int L,int mid,int R){
        int[] la = new int[mid-L];    //左边数组
        int[] ra = new int[R-mid+1];      //右边数组
        //填充
        for(int i  = L;i<mid; i++){
            la[i-L] = a[i];
        }
        for(int i = mid;i<=R;i++){
            ra[i-mid] = a[i];
        }

        int i = 0,j = 0;
        int k =L;
        //依次比较后放入
        while(i<la.length&&j<ra.length){
            if(la[i]<ra[j]){
                a[k] = la[i];
                i++;
            }else{
                a[k] = la[j];
                j++;
            }
            k++;
        }
        while(i<la.length){
            a[k] = la[i];
            k++;
            i++;
        }
        while(j<ra.length){
            a[k] = ra[j];
            k++;
            j++;
        }

        //另一种实现
    //    int i = L;
    //    int j = mid;
        for(int n =L;n<=R;n++ ){
            if(i>mid){  //左边序列插入完毕，右边序列还有剩余
                a[n] = ra[j++];
            }else if(j>R){  //左边序列还有剩余
                a[n] = la[i++];
            }else if(la[i]<=ra[j]){   //应插入较小的左边序列的元素
                a[n] = la[i++];
            }else{
                a[n] = ra[j++];
            }
        }
    }

    //归并排序的优化
    public void mergeSort1(int[] a,int L,int R){
        if(R-L<7){  //插入排序，第一次优化
            for(int i = L+1;i<R;i++){
                int temp = a[i];
                int j = i;
                while(j>=1&&a[j-1]<temp){
                    a[j] = a[j-1];
                    j--;
                }
                a[j] = temp;
            }
            return;
        }
        int mid = (L+R)/2;
        mergeSort1(a,L,mid);
        mergeSort1(a,mid+1,R);
        //第二次优化
        //判断是否需要归并
        if(a[mid]<=a[mid+1]){
            return;
        }
        merge(a,L,mid,R);
    }

    /**
     * 第三次优化，优先创建一个数组空间用来进行归并，所以只使用一次开辟空间
     * @param a 原数组
     * @param v 开辟的工具人空间，和a一样大
     * @param L 左边界
     * @param R 右边界
     */
    public void mergeSort(int[] a,int[] v,int L,int R){

    }

    public void merge(int[] a,int[] v,int L,int R){
        //先将L到R的所有元素放入V中，V中元素的下标和L、R一致就行，因为v和a一样大
        //然后进行归并
    }

    public static int partition(int []array,int lo,int hi){
        //左右中抽取3个点，按照213的顺序排序，以左节点2作为pivot
        int mid=lo+(hi-lo)/2;
        if(array[mid]>array[hi]){
            swap(array[mid],array[hi]);
        }
        if(array[lo]>array[hi]){
            swap(array[lo],array[hi]);
        }
        if(array[mid]>array[lo]){
            swap(array[mid],array[lo]);
        }
        int key=array[lo]; //此时左节点lo值为key。后续准备放置到lo和hi重合的位置

        while(lo<hi){
            //从右开始找到比key值小的数据，写入lo
            while(array[hi]>=key&&hi>lo){
                hi--;
            }
            array[lo]=array[hi];

            //从左开始找到比key值大的数据，写入hi
            while(array[lo]<=key&&hi>lo){
                lo++;
            }
            array[hi]=array[lo];
        }

//        lo和hi重合时候,将key放入。此时hi左面的数都小于key，右面数大于key
        array[hi]=key;
        return hi;
    }

    public static void swap(int a,int b){
        int temp=a;
        a=b;
        b=temp;
    }

    //希尔排序
    public void shellSort(int[] a){
        for(int step = a.length/2;step>0;step/=2){
            for(int i = step;i<a.length;i++){
                int temp = a[i];
                int j = i;
                while(j-step>=0&&a[j-step]>temp){
                    a[j] = a[j-step];
                    j-=step;
                }
                a[j] = temp;
            }
        }
    }


    /**
     * 基数排序（桶排序），时间复杂度为O（d(R+N)）,d为最高位数，R为桶个数，这里是10，N为数组个数。
     * 时间快，但空间占用多
     * @param a
     */
    public void radixSort(int[] a){
        int max = a[0];
        int k = 10; //桶数
        int[][] re = new int[k][a.length];  //桶
        int[] num = new int[k]; //  每个桶中元素个数
        //找最大值
        for(int i = 0;i<a.length;i++){
            if(max<a[i]) max = a[i];
        }
        for(int i = 1;max/i>0;i = i*k){     //次数为最高位数d
            //分配，插入元素到对应桶中
            for(int j = 0;j<a.length;j++){
                int index = (a[j]/i)%10;       //取相应位上的值
                re[index][num[index]++] = a[j];   //放入桶中
            }
            int in = 0; //原数组下标
            //收集，从桶中取元素
            for(int j = 0;j<k;j++){
                //从不空的桶中取数据
                if(num[j]!=0){
                    for(int n = 0;n<num[j];n++){
                        a[in++] = re[j][n];
                    }
                }
                num[j] = 0;
            }
        }
    }

    /**
     * 堆排序
     * https://mp.weixin.qq.com/s?__biz=MzI4Njg5MDA5NA==&mid=2247484064&idx=2&sn=d308d573df43e8e3b0633d0dc1147c3b&scene=19#wechat_redirect
     * @param a 数组
     */
    public void heapSort(int[] a){
        for(int i =0;i<a.length;i++){
            //i是代表了第几个数排好序
            maxHeapify(a,a.length-i);
            swap(a,0,a.length-i-1);
        }
    }

    /**
     * 完成一次建堆，最大值在堆的顶部(根节点)
     * 从尾部开始
     */
    public void maxHeapify(int[] arrays, int size) {
        // 从数组的尾部开始，直到第一个元素(角标为0)
        for (int i = size - 1; i >= 0; i--) {
            setheap(arrays, i, size);
        }
    }


    /**
     * 建堆
     * @param a 只能适用于完全二叉树
     * @param rootNode  当前父节点位置
     * @param size  二叉树节点总数
     */
    private void setheap(int[] a,int rootNode,int size){
        if(rootNode<size){
            //左右子树
            int left = 2*rootNode+1;
            int right = 2*rootNode+2;

            int max = rootNode;
            if(left<size){  //存在左子树
                if(a[left]>a[max]){
                    max = left;
                }
            }

            if(right<size){ //存在右子树
                if(a[right]>a[max]){
                    max = right;
                }
            }

            if(max!=rootNode){  //只要不是当前根节点，则交换
                swap(a,rootNode,max);
                setheap(a,max,a.length);
            }

        }
    }


}