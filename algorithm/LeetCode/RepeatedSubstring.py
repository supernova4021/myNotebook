# If the string S has repeated block, it could be described in terms of pattern.
# S = SpSp (For example, S has two repeatable block at most)
# If we repeat the string, then SS=SpSpSpSp.
# Destroying first and the last pattern by removing each character, we generate a new S2=SxSpSpSy.
# If the string has repeatable pattern inside, S2 should have valid S in its string.

class Solution:
    def repeatedSubstringPattern(self, s):
        """
        :type s: str
        :rtype: bool
        """
        return s in (s+s)[1:-1]