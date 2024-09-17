import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Arrays_Hashing {


    public static void main(String[] args) {

    }

    // 4:15 minutes
    public boolean containsDuplicate(int[] nums) {
        HashMap<Integer,Integer> dict = new HashMap<>();
        for (Integer num : nums) {

            if (dict.containsKey(num)) {
                return true;
            } else {
                dict.put(num,1);
            }
        }
        return false;
    }

    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        HashMap<Character, Integer> map1 = new HashMap<>();
        HashMap<Character, Integer> map2 = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            map1.merge(s.charAt(i), 1, Integer::sum);
            map2.merge(t.charAt(i), 1, Integer::sum);
        }

        return map1.equals(map2);
    }

    // 6 minutes
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer,Integer> seen = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (seen.containsKey(nums[i])) {
                return new int[] {seen.get(nums[i]), i};
            }
            int diff = target - nums[i];
            seen.put(diff, i);

        }
        return new int[0];
    }

    // 13 Minutes
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> strMap = new HashMap<>();
        for (String s : strs) {
            char[] charArr = s.toCharArray();
            Arrays.sort(charArr);
            String key = new String(charArr);
            if (!strMap.containsKey(key)) {
                strMap.put(key, new ArrayList<>());
            }
            strMap.get(key).add(s);
        }
        return new ArrayList<>(strMap.values());
    }
}



