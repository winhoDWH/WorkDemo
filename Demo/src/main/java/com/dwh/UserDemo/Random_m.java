package com.dwh.UserDemo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Random_m {

    public static void main(String[] args){
        List<Integer> a= new ArrayList<>() ;
        /*Integer[] b={1, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 1,0,0,0,0,1,0,0,0,1,1};
        List<Integer> a= Arrays.asList(b);*/
        List<String> list = new ArrayList<>();
        for(int i = 0;i<13;i++){
            list.add("100"+i);
        }
        java.util.Random r = new java.util.Random();
        a.add(1);
        for(int i = 1; i<13;i++){
            a.add(r.nextInt(2));
        }

        // List<String> list2 = passnode.getOrgPassId();
        System.out.println(list);
        System.out.println(a);
        System.out.println(change(list,a,true));
        System.out.println(addPassNode(a,2));
        System.out.println(change(list,a,true));
        System.out.println(change(list,a,false));


        /*passnode.setOrgPassId(change(list,a,index));
        System.out.println(list2);
        System.out.println(changeIntList(list,list2));*/


        /*for (int i = 0; i<20;i++){
            System.out.println("第"+i+"次");
            test();
        }*/
    }

    public static  void test(){
        Integer[] b={1, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 1,0,0,0,0,1,0,0,0,1,1};
        List<Integer> a= Arrays.asList(b);
        System.out.println(a);
        addPassNode(a,2);
        System.out.println();
        System.out.println(a);
    }

    public static List<Integer> addPassNode(List<Integer> arrive,int n){
        java.util.Random random = new java.util.Random();
        List<Integer> list = new ArrayList<Integer>();
        int count = 0;
        //找出值为0的数组下标
        for(int i : arrive){
            if(i==0){
                list.add(count);
            }
            count++;
        }
        System.out.print("替换位置");
        //用对这个下标list取随机数
        for(int i =0; i<n; i++){
            int index = random.nextInt(list.size());
            System.out.print(list.get(index));
            System.out.print(" ");
            arrive.set(list.get(index),1);
            list.remove(index);
        }
        System.out.println();
        return arrive;
    }
    //二进制序列转换为orgpassid序列；changOrN为真，则加@
    public static List<String> change(List<String> orgpassid, List<Integer> arrive, boolean changOrN){
        List<String> result= new ArrayList<>();
        int lastnodeindex=-1;
        if(changOrN){
            int index =0;
            for (int i : arrive){
                if(i==1)
                    lastnodeindex = index;
                index++;
            }
        }
        int count = 0;
        for(int i:arrive){
            if(i==1){
                result.add(orgpassid.get(count));
            }else{
                if(count<lastnodeindex){
                    result.add(orgpassid.get(count)+"@");
                }
            }
            count++;
        }
        return result;
    }

    public static List<Integer> changeIntList(List<String> p, List<String> arrive){
        List<Integer> result=new ArrayList<>();
        int count = 0;
        for(int i =0;i<arrive.size();i++){
            for(int j =count;j<p.size();j++){
                if(arrive.get(i).equals(p.get(j))){
                    result.set(count,1);
                    break;
                }
                result.set(count,0);
            }
            count++;
        }
        return result;
    }
}
