package com.dwh.redis;
/**
 * @author dengwenhao
 * data 2023-01-16
 */
public class Student {

    public static void main(String[] args) {
        int[] nums = new int[]{-1,0,5};
        int target = 5;
        System.out.println(search(nums, target));
    }

    public static int search(int[] nums, int target) {
        if (nums == null){
            return -1;
        }
        int mid = nums.length/2;
        int left = 0;
        int right = nums.length - 1;
        while(left <= right){
            if (nums[mid] == target){
                return mid;
            }
            if (left == right){
                return -1;
            }
            if (nums[mid] > target){
                right = mid - 1;
                mid = (left + right)/2;

            }
            if (nums[mid] < target){
                left = mid + 1;
                mid = (left + right)/2;
            }
        }
        return -1;
    }
}
