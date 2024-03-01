import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

class Solution {
    private static class Point{
        public final long x, y;
        
        private Point(long x, long y){
            this.x = x;
            this.y = y;
        }
        
        //toString() 오버라이드
        // @Override
        // public String toString(){
        //     return "(" + x + ", " + y + ")";
        // }    
    }    
    
    private Point intersaction(long a1, long a2, long a3, long b1, long b2, long b3){
        long bunmo = a1*b2 - a2*b1;
        double x = (double)(a2*b3 - a3*b2)/bunmo;
        double y = (double)(a3*b1 - a1*b3)/bunmo;
        
        if(x % 1 != 0 || y % 1 != 0) return null;
        return new Point((long)x, (long)y);
    }
    
    private Point getMinimum(List<Point> points){
        long x = Long.MAX_VALUE;
        long y = Long.MAX_VALUE;
        
        for(Point p:points){
            if(x > p.x) x = p.x;
            if(y > p.y) y = p.y;
        }
        
        return new Point(x, y);
    }
    
    private Point getMaximum(List<Point> points){
        long x = Long.MIN_VALUE;
        long y = Long.MIN_VALUE;
        
        for(Point p:points){
            if(x < p.x) x = p.x;
            if(y < p.y) y = p.y;
        }
        
        return new Point(x, y);
    }
    
    public String[] solution(int[][] line) {
        List<Point> points = new ArrayList<>();
        for(int i = 0; i < line.length;i++){
            for(int z = i + 1; z < line.length; z++){
                Point star = intersaction(line[i][0], line[i][1], line[i][2], line[z][0], line[z][1], line[z][2]);
                
                if(star != null){
                    points.add(star);
                }   
            }
        }
        
        Point minimum = getMinimum(points);
        Point maximum = getMaximum(points);
        
        int width = (int)(maximum.x - minimum.x + 1);
        int height = (int)(maximum.y - minimum.y + 1);

        //char[][]이 아닌 String[] 배열을 이용하여 반복문을 줄이려고 함
        String[] answer = new String[height];
        for(int i = 0; i < answer.length; i++){
            //repeat 함수 사용
            answer[i] = ".".repeat(width);
        }
        
        for(Point p: points){
            int x = (int)(p.x - minimum.x);
            int y = (int)(maximum.y - p.y);

            //특정 위치의 문자열 변경을 위해 StringBuffer의 replace 함수 사용
            StringBuffer sb = new StringBuffer();
            sb.append(answer[y]);
            sb.replace(x, x+1, "*");
            answer[y] = sb.toString();
        }
        
        return answer;
    }
}
