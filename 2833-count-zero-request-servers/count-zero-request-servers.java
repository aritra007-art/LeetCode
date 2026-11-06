class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        int m=queries.length;
        int []ans = new int[m];

        Arrays.sort(logs,(a,b)->Integer.compare(a[1],b[1]));
        int[][] indexedQueries = new int[m][2];
        for(int i=0;i<m;i++){
            indexedQueries[i][0]=queries[i];
            indexedQueries[i][1]=i;
        }

        Arrays.sort(indexedQueries,(a,b)->Integer.compare(a[0],b[0]));

        int [] serverRequestCount=new int[n+1];
        int activeServers=0;
        int left=0,right=0;

        for(int i=0;i<m;i++){
            int queryTime = indexedQueries[i][0];
            int originalIdx = indexedQueries[i][1];

            while(right<logs.length && logs[right][1]<=queryTime){
                int serverId = logs[right][0];
                if(serverRequestCount[serverId]==0){
                    activeServers++;

                }
                serverRequestCount[serverId]++;
                right++;
            }
            while(left<logs.length && logs[left][1]<queryTime-x){
                int serverId = logs[left][0];
                serverRequestCount[serverId]--;
                if(serverRequestCount[serverId]==0){
                    activeServers--;
                }
                left++;     
            }
            ans[originalIdx]=n-activeServers;
        }
        return ans;
    }
}