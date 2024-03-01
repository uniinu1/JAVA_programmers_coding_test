class Solution {
    public int[] solution(int n) {
        int[][] answer = new int[n][n];
        int[] answer2 = new int[n*(n+1)/2];
        
        int[] dx = {0, 1, -1};
        int[] dy = {1, 0, -1};
        
        int rep = n;
        int oneRep = 0;
        int d = 0;
        int x = 0;
        int y = 0;
        for(int i = 0; i < n*(n+1)/2 ; i++){
            if(i == 0){
                answer[y][x] = 1;
                oneRep = 1;
                continue;
            }else{
                if(oneRep < rep){
                    oneRep += 1;
                }else{
                    oneRep = 1;
                    if(d == 2){
                        d = 0;
                    }else{
                        d += 1;
                    }
                    
                    rep -= 1;
                    if(rep == 0) break;
                }
                y = y + dy[d];
                x = x + dx[d];
               //System.out.println(y);
                
                answer[y][x] = i + 1;
            }
        }
        
        int i = 0;
        for(int a = 0; a < n; a++){
            for(int b = 0; b < a + 1; b++){
                answer2[i] = answer[a][b];
                i++;
            }
        }
        return answer2;
    }
}
