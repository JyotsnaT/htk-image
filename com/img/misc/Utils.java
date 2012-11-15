package com.img.misc;

import java.util.HashMap;
import java.util.Map;

public class Utils {
public static Map<Double, int[]> LableColorMap=new HashMap<>();
public static Map<Character, Double> labelMap = new HashMap<>();
static{
	int[] a_color={0,0,0};
	int[] b_color={128,0,0};
	int[] c_color={0,128,0};
	int[] d_color={128,128,0};
	int[] e_color={0,0,128};
	int[] f_color={128,0,128};
	int[] g_color={0,128,128};
	int[] h_color={128,128,128};
	int[] i_color={64,0,0};
	int[] j_color={192,0,0};
	int[] k_color={64,128,0};
	int[] l_color={192,128,0};
	int[] m_color={64,0,128};
	int[] n_color={192,0,128};
	int[] o_color={64,128,128};
	int[] p_color={192,128,128};
	int[] q_color={0,64,0};
	int[] r_color={128,64,0};
	int[] s_color={0,192,0};
	int[] t_color={128,192,0};
	int[] u_color={0,64,128};
	int[] z_color={224,224,192};
	LableColorMap.put(0.0, a_color);
	LableColorMap.put(1.0, b_color);
	LableColorMap.put(2.0, c_color);
	LableColorMap.put(3.0, d_color);
	LableColorMap.put(4.0, e_color);
	LableColorMap.put(5.0, f_color);
	LableColorMap.put(6.0, g_color);
	LableColorMap.put(7.0, h_color);
	LableColorMap.put(8.0, i_color);
	LableColorMap.put(9.0, j_color);
	LableColorMap.put(10.0, k_color);
	LableColorMap.put(11.0, l_color);
	LableColorMap.put(12.0, m_color);
	LableColorMap.put(13.0, n_color);
	LableColorMap.put(14.0, o_color);
	LableColorMap.put(15.0, p_color);
	LableColorMap.put(16.0, q_color);
	LableColorMap.put(17.0, r_color);
	LableColorMap.put(18.0, s_color);
	LableColorMap.put(19.0, t_color);
	LableColorMap.put(20.0, u_color);
	LableColorMap.put(255.0, z_color);
	
	labelMap.put('A', 0.0 );		// background
	labelMap.put('B', 1.0 );		// airplane
	labelMap.put('C', 2.0 );		// bicycle
	labelMap.put('D', 3.0 );		// bird
	labelMap.put('E', 4.0 );		// boat
	labelMap.put('F', 5.0);		// bottle
	labelMap.put('G', 6.0);		// bus
	labelMap.put('H', 7.0);		// car
	labelMap.put('I', 8.0);		// cat
	labelMap.put('J', 9.0);		// chair
	labelMap.put('K', 10.0);	// cow
	labelMap.put('L', 11.0);	// dining table
	labelMap.put('M', 12.0);	// dog
	labelMap.put('N', 13.0);	// horse
	labelMap.put('O', 14.0);	// motorcycle
	labelMap.put('P', 15.0);	// person
	labelMap.put('Q', 16.0);	// potted plant
	labelMap.put('R', 17.0);	// sheep
	labelMap.put('S', 18.0);	// sofa
	labelMap.put('T', 19.0);	// train
	labelMap.put('U', 20.0);	// TV/monitor
	labelMap.put('Z', 255.0);	// void*/
 }

}
