package charlie.nbtweak;

import java.util.HashMap;
import java.util.Map;

public class RealPianoMapping {
	private static final Map<String, Integer> map = new HashMap<String, Integer>();
	public static void init(){
		//Upper grp 2
		map.put("A2", 21);
		map.put("A#2", 22);
		map.put("Bb2", 22);
		map.put("B2", 23);
		//Upper grp 1
		map.put("C1", 24);
		map.put("C#1", 25);
		map.put("Db1", 25);
		map.put("D1", 26);
		map.put("D#1", 27);
		map.put("Eb1", 27);
		map.put("E1", 28);
		map.put("F1", 29);
		map.put("F#1", 30);
		map.put("Gb1", 30);
		map.put("G1", 31);
		map.put("G#1", 32);
		map.put("Ab1", 32);
		map.put("A1", 33);
		map.put("A#1", 34);
		map.put("Bb1", 34);
		map.put("B1", 35);
		//Upper grp
		map.put("C", 36);
		map.put("C#", 37);
		map.put("Db", 37);
		map.put("D", 38);
		map.put("D#", 39);
		map.put("Eb", 39);
		map.put("E", 40);
		map.put("F", 41);
		map.put("F#", 42);
		map.put("Gb", 42);
		map.put("G", 43);
		map.put("G#", 44);
		map.put("Ab", 44);
		map.put("A", 45);
		map.put("A#", 46);
		map.put("Bb", 46);
		map.put("B", 47);
		//Lower grp
		map.put("c", 48);
		map.put("c#", 49);
		map.put("db", 49);
		map.put("d", 50);
		map.put("d#", 51);
		map.put("eb", 51);
		map.put("e", 52);
		map.put("f", 53);
		map.put("f#", 54);
		map.put("gb", 54);
		map.put("g", 55);
		map.put("g#", 56);
		map.put("ab", 56);
		map.put("a", 57);
		map.put("a#", 58);
		map.put("bb", 58);
		map.put("b", 59);
		//Lower grp 1
		map.put("c1", 60);
		map.put("c#1", 61);
		map.put("db1", 61);
		map.put("d1", 62);
		map.put("d#1", 63);
		map.put("eb1", 63);
		map.put("e1", 64);
		map.put("f1", 65);
		map.put("f#1", 66);
		map.put("gb1", 66);
		map.put("g1", 67);
		map.put("g#1", 68);
		map.put("ab1", 68);
		map.put("a1", 69);
		map.put("a#1", 70);
		map.put("bb1", 70);
		map.put("b1", 71);
		//Lower grp 2
		map.put("c2", 72);
		map.put("c#2", 73);
		map.put("db2", 73);
		map.put("d2", 74);
		map.put("d#2", 75);
		map.put("eb2", 75);
		map.put("e2", 76);
		map.put("f2", 77);
		map.put("f#2", 78);
		map.put("gb2", 78);
		map.put("g2", 79);
		map.put("g#2", 80);
		map.put("ab2", 80);
		map.put("a2", 81);
		map.put("a#2", 82);
		map.put("bb2", 82);
		map.put("b2", 83);
		//Lower grp 3
		map.put("c3", 84);
		map.put("c#3", 85);
		map.put("db3", 85);
		map.put("d3", 86);
		map.put("d#3", 87);
		map.put("eb3", 87);
		map.put("e3", 88);
		map.put("f3", 89);
		map.put("f#3", 90);
		map.put("gb3", 90);
		map.put("g3", 91);
		map.put("g#3", 92);
		map.put("ab3", 92);
		map.put("a3", 93);
		map.put("a#3", 94);
		map.put("bb3", 94);
		map.put("b3", 95);
		//Lower grp 4
		map.put("c4", 96);
		map.put("c#4", 97);
		map.put("db4", 97);
		map.put("d4", 98);
		map.put("d#4", 99);
		map.put("eb4", 99);
		map.put("e4", 100);
		map.put("f4", 101);
		map.put("f#4", 102);
		map.put("gb4", 102);
		map.put("g4", 103);
		map.put("g#4", 104);
		map.put("ab4", 104);
		map.put("a4", 105);
		map.put("a#4", 106);
		map.put("bb4", 106);
		map.put("b4", 107);
		//Lower grp 5
		map.put("c5", 108);
	}
	
	public static int get(String note){
		return !map.containsKey(note) ? -1 : map.get(note);
	}
}
