package main;  

import java.util.*;
import java.util.stream.Stream;

public class Solution implements PuzzleResolver{ // метод resolve на стадии разработки

   public int arrayToInt(int[]someMass){  // перевод из массива в целое число
       StringBuilder s = new StringBuilder();
         for (int k: someMass){
             s.append(k);
         }
         return Integer.parseInt(s.toString());
   }
   public  int[] intToArray(int someVar){  // перевод целого числа в массив
       int[] p = Stream.of(String.valueOf(someVar).split("")).
               mapToInt(Integer::parseInt).toArray();
       return p;
   }

    public int [] forceElements(int [] mass ,int i, int j ){ // перемещение соседних элементов от вершины графа 2, 1, 3 ,4 ,0, 5 ,6, 7 -> 2 1 3 0 5 4 6 7
        int t = mass[i];
          mass[i] = mass[j];
             mass[j]=t;
                return mass;
    }


    public int GetSiblings(int [] start ) {
          int indexOfZero = 0;
          for(int i =0; i<start.length;i++){
              if (start[i]==0){indexOfZero=start[i];}
          }

          int result =0;
          switch (indexOfZero) {
              case 0:
                   arrayToInt(forceElements(start, 0, 1));
                  arrayToInt(forceElements(start, 0, 2));
                  result = arrayToInt(forceElements(start, 0, 2));
              break;
                case 1:
                    arrayToInt(forceElements(start,1, 0));
                    arrayToInt(forceElements(start, 1, 2));
                    result =    arrayToInt(forceElements(start, 1, 3));
              break;

             case 3:
                 arrayToInt(forceElements(start, 3, 1));
                 arrayToInt(forceElements(start, 3, 4));
                 result =arrayToInt(forceElements(start, 3, 6));
              break;

              case 4:
                  arrayToInt(forceElements(start, 4, 3));

              result =  arrayToInt(forceElements(start, 4, 5));
                 break;

                case 5:
                    arrayToInt(forceElements(start, 5, 2));
                    arrayToInt(forceElements(start, 5, 4));
                    result = arrayToInt(forceElements(start, 5, 7));
              break;
              case 6:
                  arrayToInt(forceElements(start, 6, 3));
                      result = arrayToInt(forceElements(start, 6, 7));
              break;
              case 7:
                  arrayToInt(forceElements(start, 7, 6));
                  result = arrayToInt(forceElements(start, 7, 5));
              break;



      }return result;}

      public int[] resolve(int[] start){
           int [] mass = new int[8];
               int model = 12340567;
                  int user_input = arrayToInt(start);
          Deque<Integer> graphQueue = new ArrayDeque();
                   Set<Integer> visited= new HashSet<Integer>();
                     Map<Integer,Integer> map = new HashMap<Integer, Integer>();
                       graphQueue.addLast(user_input);
                       visited.add(user_input);

                             if(user_input == model)
                                 return new int[0];
                          while (graphQueue.size()>0&&user_input != model){
                              for(int i =0; i<graphQueue.size();i++){
                                  int current =graphQueue.pollLast();
                                     int[] arrayCurrent = intToArray(current);//java 8
                                           ArrayList<Integer> siblings = new ArrayList<>((GetSiblings(arrayCurrent)));
                                               for (int k =0; k<siblings.size();k++){
                                                    int s = siblings.get(k);
                                               if(visited.contains(s))continue;
                                                     visited.add(s);
                                                       graphQueue.addLast(s);
                                                          map.put(s,current);
                                                               if(s==model){
                                                                   i=graphQueue.size();
                                                                     graphQueue.clear();
                                                                        break;
                                                               }}}}

          Stack<Integer> stack = new Stack<>();
                          int[] catch1 = new int[8];
                          int[] catch2 = new int[8];
                            int c =model;

                            while (c!=user_input){
                                int prev = map.get(c);
                                  catch1 = Stream.of(String.valueOf(c).split("")).
                                          mapToInt(Integer::parseInt).toArray();
                                  catch2 =  Stream.of(String.valueOf(prev).split("")).
                                          mapToInt(Integer::parseInt).toArray();


                                  int index = 0;
                                     for (int i =0; i<catch1.length;i++){
                                         if(catch1[i]==0){
                                             index=i; break;

                                         }


                                     }
                                       stack.push(catch2[index]);
                                           c=prev;

                                 }
                            int [] result = new int[stack.size()];
                            for(int i =0; i<stack.size();i++){
                                result[i]=stack.get(i);
                            }

                            return result;




}}
