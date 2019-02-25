package DataStructures;

class Trie {
	static class TrieNode {
		TrieNode[] charMap = new TrieNode[26];
		char nodeChar;
		int leafCount;

		TrieNode(char nodeChar, int leafCount) {
			this.nodeChar = nodeChar;
			this.leafCount = leafCount;
		}

		TrieNode getNode(char c) {
			return charMap[c - 'a'];
		}

		void setNode(char c) {
			charMap[c - 'a'] = new TrieNode(c, 0);
		}

		void add(String s) {
			TrieNode n = this;

			for (int i = 0; i < s.length(); i++) {
				if (n.getNode(s.charAt(i)) == null) {
					n.setNode(s.charAt(i));
				}

				n = n.getNode(s.charAt(i));
				n.leafCount++;
			}
		}

		int findCount(String s) {
			TrieNode n = this;
			System.out.println("Looking for " + s);

			for (int i = 0; i < s.length(); i++) {
				n = n.getNode(s.charAt(i));

				if (n == null) {
					return 0;
				}
			}

			return n.leafCount;
		}
	}

}