class Solution {
    public List<String> restoreIpAddresses(String s) {
        List <String> res = new ArrayList<>();
        if(s.length()<4 || s.length()>12) return res;
        backtrack(s,0,0,new ArrayList<>(),res);
        return res;
    }
    private void backtrack(String s, int idx, int segment, List<String> path, List <String> res){
        if(segment==4 && idx==s.length()){
            res.add(String.join(".",path));
            return;
        }
        if(segment==4 || idx==s.length()){
            return;
        }

        for(int len=1;len<=3 && idx+len<=s.length();len++){
            String sub = s.substring(idx,idx+len);
            if(isValid(sub)){
                path.add(sub);
                backtrack(s,idx+len,segment+1,path,res);
                path.remove(path.size()-1);
            }
        }
    }
    private boolean isValid(String sub){
        if(sub.length()>1 && sub.charAt(0)=='0') return false;
        int val = Integer.parseInt(sub);
        return val>=0 && val<=255 ;
    }
}