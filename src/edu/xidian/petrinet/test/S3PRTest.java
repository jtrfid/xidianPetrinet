/**
 * 
 */
package edu.xidian.petrinet.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.uni.freiburg.iig.telematik.sepia.petrinet.abstr.AbstractPNNode;
import de.uni.freiburg.iig.telematik.sepia.petrinet.pt.PTNet;
import de.uni.freiburg.iig.telematik.sepia.petrinet.pt.PTPlace;
import edu.xidian.petrinet.S2PR;
import edu.xidian.petrinet.S3PR;

/**
 * @author Administrator
 *
 */
public class S3PRTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link edu.xidian.petrinet.S3PR#S3PR(S2PR)}.
	 */
	//@Test
	public void testS3PR() {
		//fail("Not yet implemented");
		S2PR s2pr = new S2PR("test_s2pr",2,1,4);
		System.out.println("s2pr:" + s2pr);
		
		S3PR s3pr = new S3PR("test_s3pr","s2pr_1",s2pr);
		System.out.println("s3pr: " + s3pr);
	}
	
	/**
	 * Test method for {@link edu.xidian.petrinet.S3PR#S3PR(PTNet, String, Collection, Collection)}.
	 */
	//@Test
	public void testS3PR1() {
		///////////////////// ptnet1
		PTNet ptnet1 = new PTNet();
		ptnet1.addPlace("p1");
		ptnet1.addTransition("t1");
		ptnet1.addPlace("p2");
		ptnet1.addTransition("t2");
		ptnet1.addPlace("p3");
		ptnet1.addTransition("t3");
		ptnet1.addPlace("p7");
		ptnet1.addPlace("p8");
		
		// p0,pa
		ptnet1.addFlowRelationPT("p1", "t1");
		ptnet1.addFlowRelationTP("t1", "p2");
		ptnet1.addFlowRelationPT("p2", "t2");
		ptnet1.addFlowRelationTP("t2", "p3");
		ptnet1.addFlowRelationPT("p3", "t3");
		ptnet1.addFlowRelationTP("t3", "p1");
		
		// pr
		ptnet1.addFlowRelationPT("p7", "t1");
		ptnet1.addFlowRelationTP("t2", "p7");
		ptnet1.addFlowRelationPT("p8", "t2");
		ptnet1.addFlowRelationTP("t3", "p8");
		
		Set<String> pa = new HashSet<String>();
		pa.add("p2"); pa.add("p3");
		Set<String> pr = new HashSet<String>();
		pr.add("p7"); pr.add("p8");
		
		///////////////////// ptnet2
		PTNet ptnet2 = new PTNet();
		ptnet2.addPlace("p4");
		ptnet2.addTransition("t4");
		ptnet2.addPlace("p5");
		ptnet2.addTransition("t5");
		ptnet2.addPlace("p6");
		ptnet2.addTransition("t6");
		ptnet2.addPlace("p7");
		ptnet2.addPlace("p8");
		
		// p0,pa
		ptnet2.addFlowRelationPT("p6", "t4");
		ptnet2.addFlowRelationTP("t4", "p4");
		ptnet2.addFlowRelationPT("p4", "t5");
		ptnet2.addFlowRelationTP("t5", "p5");
		ptnet2.addFlowRelationPT("p5", "t6");
		ptnet2.addFlowRelationTP("t6", "p6");
		
		// pr
		ptnet2.addFlowRelationPT("p7", "t5");
		ptnet2.addFlowRelationTP("t6", "p7");
		ptnet2.addFlowRelationPT("p8", "t4");
		ptnet2.addFlowRelationTP("t5", "p8");
		
		S3PR s3pr = new S3PR("test_s3pr","s2pr_1",ptnet1,"p1",pa,pr);
		System.out.println("s3pr: " + s3pr);
		
		pa.clear();
		pa.add("p4"); pa.add("p5");
		pr.clear();
		pr.add("p7"); pr.add("p8");
		s3pr.add("s2pr_in_s3pr_2",ptnet2,"p6",pa,pr);
		System.out.println("s3pr: " + s3pr);
	}
	
	/**
	 * Test method for {@link edu.xidian.petrinet.S3PR#getHr(Set)}.
	 * Li. 图3-1或图4-2
	 */
	//@Test
	public void getHrs() {
		///////////////////// ptnet1
		PTNet ptnet1 = new PTNet();
		ptnet1.addPlace("p1");
		ptnet1.addTransition("t1");
		ptnet1.addPlace("p2");
		ptnet1.addTransition("t2");
		ptnet1.addPlace("p3");
		ptnet1.addTransition("t3");
		ptnet1.addPlace("p7");
		ptnet1.addPlace("p8");
		
		// p0,pa
		ptnet1.addFlowRelationPT("p1", "t1");
		ptnet1.addFlowRelationTP("t1", "p2");
		ptnet1.addFlowRelationPT("p2", "t2");
		ptnet1.addFlowRelationTP("t2", "p3");
		ptnet1.addFlowRelationPT("p3", "t3");
		ptnet1.addFlowRelationTP("t3", "p1");
		
		// pr
		ptnet1.addFlowRelationPT("p7", "t1");
		ptnet1.addFlowRelationTP("t2", "p7");
		ptnet1.addFlowRelationPT("p8", "t2");
		ptnet1.addFlowRelationTP("t3", "p8");
		
		Set<String> pa = new HashSet<String>();
		pa.add("p2"); pa.add("p3");
		Set<String> pr = new HashSet<String>();
		pr.add("p7"); pr.add("p8");
		
		S3PR s3pr = new S3PR("Li_figure3-1_or_4-2","s2pr_in_s3pr_1",ptnet1,"p1",pa,pr);
		
		///////////////////// ptnet2
		PTNet ptnet2 = new PTNet();
		ptnet2.addPlace("p4");
		ptnet2.addTransition("t4");
		ptnet2.addPlace("p5");
		ptnet2.addTransition("t5");
		ptnet2.addPlace("p6");
		ptnet2.addTransition("t6");
		ptnet2.addPlace("p7");
		ptnet2.addPlace("p8");
		
		// p0,pa
		ptnet2.addFlowRelationPT("p6", "t4");
		ptnet2.addFlowRelationTP("t4", "p4");
		ptnet2.addFlowRelationPT("p4", "t5");
		ptnet2.addFlowRelationTP("t5", "p5");
		ptnet2.addFlowRelationPT("p5", "t6");
		ptnet2.addFlowRelationTP("t6", "p6");
		
		// pr
		ptnet2.addFlowRelationPT("p7", "t5");
		ptnet2.addFlowRelationTP("t6", "p7");
		ptnet2.addFlowRelationPT("p8", "t4");
		ptnet2.addFlowRelationTP("t5", "p8");
		
		pa.clear();
		pa.add("p4"); pa.add("p5");
		pr.clear();
		pr.add("p7"); pr.add("p8");
		s3pr.add("s2pr_in_s3pr_2",ptnet2,"p6",pa,pr);
		System.out.println("s3pr: " + s3pr);
		
		//////////////////////////////////////////////////
		// SR: 信标S中的资源库所集合
		Set<PTPlace> SR = new HashSet<>();
		SR.add(s3pr.getPlace("p7"));
		SR.add(s3pr.getPlace("p8"));
		Collection<PTPlace> Hrs = s3pr.getHr(SR);
		System.out.println("Hrs: " + Hrs); // Hrs: [p4[p4], p5[p5], p3[p3], p2[p2]]
		assertEquals(4, Hrs.size());
		
		// Hrs = H(p7) ∪ H(p8)
		System.out.println("H(p7) = " + s3pr.getHr(s3pr.getPlace("p7"))); // H(p7) = [p5[p5], p2[p2]]
		System.out.println("H(p8) = " + s3pr.getHr(s3pr.getPlace("p8"))); // H(p8) = [p4[p4], p3[p3]]
		
		// 信标S
		Collection<PTPlace> S = new HashSet<>();
		S.add(s3pr.getPlace("p3"));  S.add(s3pr.getPlace("p5"));
		S.add(s3pr.getPlace("p7"));  S.add(s3pr.getPlace("p8"));
		// 信标补集[S] = Hrs \ S
		Collection<PTPlace> SS = new HashSet<>();
		SS.addAll(Hrs);
		SS.removeAll(S);
		System.out.println("信标补集：" + SS); // 信标补集：[p4[p4], p2[p2]]
		
		// 排序
		List<PTPlace> SS1 = new ArrayList<>(SS);
		Collections.sort(SS1, new Comparator<PTPlace>(){
			@Override
			public int compare(PTPlace o1, PTPlace o2) {
				String s1 = o1.getName();
				String s2 = o2.getName();
				return s1.compareTo(s2);
			}});
		System.out.println("排序后的信标补集：" + SS1); // 排序后的信标补集：[p2[p2], p4[p4]]
		
		// 直接调用函数，求信标S的补集
		System.out.println("信标补集：" + s3pr.getSiphonCom(SR, S)); // 信标补集：[p4[p4], p2[p2]]
		
	}
	
	/**
	 * test set, ==, equals
	 * Set中集合运算的基础：equals判断是否两个元素相同，不是通过"=="判断的
	 */
	@SuppressWarnings("rawtypes")
	@Test
	public void testSet() {
		S2PR s2pr = new S2PR();
		s2pr.addPlace("p1");
		s2pr.addPlace("p2");
		s2pr.addPlace("p3");
		S3PR s3pr = new S3PR();
		s3pr.addPlace("p3");
		s3pr.addPlace("p4");
		
		Collection<PTPlace> places = new HashSet<>();
		// places.add(s3pr.getPlace("p3")))失败，
		// 因为在此之前,有places.add(s2pr.getPlace("p3")),
		// places通过equals判断，已经有"p3"对应的place，因此不用增加了。
		System.out.println("add ok = " + 
		        places.add(s2pr.getPlace("p1")) + "," +
				places.add(s2pr.getPlace("p3")) + "," + 
		        places.add(s3pr.getPlace("p3"))); // add ok = true,true,false
		System.out.println("places: " + places);  // places: [p1[p1], p3[p3]]
		boolean b1 = (s2pr.getPlace("p3") == s3pr.getPlace("p3")); // flase
		boolean b2 = (s2pr.getPlace("p3").equals(s3pr.getPlace("p3"))); // true
		System.out.println("两个p3  ！==,但是equals： " + b1 + "," + b2);
		
		Collection<PTPlace> ps1 = new HashSet<>();
		Collection<PTPlace> ps2 = new HashSet<>();
		s2pr.setName("net1");
		s3pr.setName("net2");
		ps1.add(s2pr.getPlace("p3"));
		ps2.add(s3pr.getPlace("p3"));
		ps1.retainAll(ps2); 
		System.out.println("虽然不是同一个place,但是他们的交集相同，因为equals相等，ps1=" + ps1);
		
		//Collection<AbstractPNNode> ps11 = new HashSet<>();
		//Collection<PTPlace> ps22 = new HashSet<>();
		Set<AbstractPNNode> ps11 = new HashSet<>();
		Set<PTPlace> ps22 = new HashSet<>();
		ps11.add(s2pr.getPlace("p3"));
		ps22.add(s3pr.getPlace("p3"));
		ps11.retainAll(ps22); 
		System.out.println("虽然不是同一个place,但是他们的交集相同，因为equals相等，ps11=" + ps11);
	}
	
	/**
	 * Test method for {@link edu.xidian.petrinet.S3PR#s3pr4_1()}.
	 * Li. p68, 图4-3
	 */
	//@Test
	public void s3pr4_1() {
		///////////////////// ptnet1
		PTNet ptnet1 = new PTNet();
		// p0
		ptnet1.addPlace("p1");
		// pa
		ptnet1.addTransition("t1");
		ptnet1.addPlace("p2");
		ptnet1.addTransition("t2");
		ptnet1.addPlace("p3");
		ptnet1.addTransition("t3");
		ptnet1.addPlace("p4");
		ptnet1.addTransition("t4");
		ptnet1.addPlace("p5");
		ptnet1.addTransition("t5");
		ptnet1.addPlace("p6");
		ptnet1.addTransition("t6");
		ptnet1.addTransition("t7");
		
		ptnet1.addPlace("p22");
		ptnet1.addTransition("t18");
		// pr
		ptnet1.addPlace("p16");
		ptnet1.addPlace("p19");
		ptnet1.addPlace("p20");
		ptnet1.addPlace("p21");
			
		// p0,pa Flows,回路1
		ptnet1.addFlowRelationPT("p1", "t1");
		ptnet1.addFlowRelationTP("t1", "p2");
		ptnet1.addFlowRelationPT("p2", "t2");
		ptnet1.addFlowRelationTP("t2", "p3");
		ptnet1.addFlowRelationPT("p3", "t3");
		ptnet1.addFlowRelationTP("t3", "p4");
		ptnet1.addFlowRelationPT("p4", "t4");
		ptnet1.addFlowRelationTP("t4", "p1");
		// p0,pa Flows,回路2
		ptnet1.addFlowRelationPT("p1", "t18");
		ptnet1.addFlowRelationTP("t18", "p22");
		ptnet1.addFlowRelationPT("p22", "t5");
		ptnet1.addFlowRelationTP("t5", "p5");
		ptnet1.addFlowRelationPT("p5", "t6");
		ptnet1.addFlowRelationTP("t6", "p6");
		ptnet1.addFlowRelationPT("p6", "t7");
		ptnet1.addFlowRelationTP("t7", "p1");
		
		// pr Flows
		ptnet1.addFlowRelationPT("p19", "t18");
		ptnet1.addFlowRelationTP("t5", "p19");
		ptnet1.addFlowRelationPT("p16", "t5");
		ptnet1.addFlowRelationTP("t6", "p16");
		ptnet1.addFlowRelationPT("p19", "t6");
		ptnet1.addFlowRelationTP("t7", "p19");
		
		ptnet1.addFlowRelationPT("p20", "t1");
		ptnet1.addFlowRelationTP("t2", "p20");
		ptnet1.addFlowRelationPT("p21", "t2");
		ptnet1.addFlowRelationTP("t3", "p21");
		ptnet1.addFlowRelationPT("p20", "t3");
		ptnet1.addFlowRelationTP("t4", "p20");
		
		Set<String> pa = new HashSet<String>();
		pa.add("p2"); pa.add("p3"); pa.add("p4"); pa.add("p5");
		pa.add("p6"); pa.add("p22");
		Set<String> pr = new HashSet<String>();
		pr.add("p16"); pr.add("p19"); pr.add("p20"); pr.add("p21");
		
		S3PR s3pr = new S3PR("Li_p68_figure4-3","s2pr_1",ptnet1,"p1",pa,pr);
		System.out.println("1: s3pr: " + s3pr);

		///////////////////// ptnet2
		PTNet ptnet2 = new PTNet();
		// p0
		ptnet2.addPlace("p7");
		// pa
		ptnet2.addTransition("t8");
		ptnet2.addPlace("p8");
		ptnet2.addTransition("t9");
		ptnet2.addPlace("p9");
		ptnet2.addTransition("t10");
		ptnet2.addPlace("p10");
		ptnet2.addTransition("t11");
		
		ptnet2.addTransition("t12");
		ptnet2.addPlace("p11");
		ptnet2.addTransition("t13");
	
		// pr
		ptnet2.addPlace("p16");
		ptnet2.addPlace("p17");
		ptnet2.addPlace("p18");
		ptnet2.addPlace("p19");

		// p0,pa Flows,回路1
		ptnet2.addFlowRelationPT("p7", "t8");
		ptnet2.addFlowRelationTP("t8", "p8");
		ptnet2.addFlowRelationPT("p8", "t9");
		ptnet2.addFlowRelationTP("t9", "p9");
		ptnet2.addFlowRelationPT("p9", "t10");
		ptnet2.addFlowRelationTP("t10", "p10");
		ptnet2.addFlowRelationPT("p10", "t11");
		ptnet2.addFlowRelationTP("t11", "p7");
		// p0,pa Flows,回路2
		ptnet2.addFlowRelationPT("p9", "t12");
		ptnet2.addFlowRelationTP("t12", "p11");
		ptnet2.addFlowRelationPT("p11", "t13");
		ptnet2.addFlowRelationTP("t13", "p7");

		// pr Flows
		ptnet2.addFlowRelationPT("p16", "t8");
		ptnet2.addFlowRelationTP("t9", "p16");
		ptnet2.addFlowRelationPT("p17", "t9");
		ptnet2.addFlowRelationTP("t10", "p17");
		
		ptnet2.addFlowRelationTP("t12", "p17");
		ptnet2.addFlowRelationPT("p18", "t12");
		ptnet2.addFlowRelationTP("t13", "p18");

		ptnet2.addFlowRelationPT("p19", "t10");
		ptnet2.addFlowRelationTP("t11", "p19");
		
		pa.clear();
		pa.add("p8"); pa.add("p9");	pa.add("p10"); pa.add("p11");
		
		pr.clear();
		pr.add("p16"); 	pr.add("p17"); pr.add("p18");pr.add("p19");

		s3pr.add("s2pr_2",ptnet2, "p7", pa, pr);
		System.out.println("2: s3pr: " + s3pr);
		
		///////////////////// ptnet3
		PTNet ptnet3 = new PTNet();
		// p0
		ptnet3.addPlace("p12");
		// pa
		ptnet3.addTransition("t14");
		ptnet3.addPlace("p13");
		ptnet3.addTransition("t15");
		ptnet3.addPlace("p14");
		ptnet3.addTransition("t16");
		ptnet3.addPlace("p15");
		ptnet3.addTransition("t17");
	
		// pr
		ptnet3.addPlace("p16");
		ptnet3.addPlace("p17");
		ptnet3.addPlace("p18");

		// p0,pa Flows,回路1
		ptnet3.addFlowRelationPT("p12", "t14");
		ptnet3.addFlowRelationTP("t14", "p13");
		ptnet3.addFlowRelationPT("p13", "t15");
		ptnet3.addFlowRelationTP("t15", "p14");
		ptnet3.addFlowRelationPT("p14", "t16");
		ptnet3.addFlowRelationTP("t16", "p15");
		ptnet3.addFlowRelationPT("p15", "t17");
		ptnet3.addFlowRelationTP("t17", "p12");
		
		// pr Flows
		ptnet3.addFlowRelationPT("p16", "t16");
		ptnet3.addFlowRelationTP("t17", "p16");
		ptnet3.addFlowRelationPT("p17", "t15");
		ptnet3.addFlowRelationTP("t16", "p17");
		ptnet3.addFlowRelationPT("p18", "t14");
		ptnet3.addFlowRelationTP("t15", "p18");
		
		pa.clear();
		pa.add("p13"); pa.add("p14");	pa.add("p15"); 
		
		pr.clear();
		pr.add("p16"); 	pr.add("p17"); pr.add("p18");

		s3pr.add("s2pr_3",ptnet3, "p12", pa, pr);
		System.out.println("3: s3pr: " + s3pr);
		
		System.out.println("s3pr4_1()===============");
		s3pr.s3pr4_1();
		
		// siphons
		Collection<PTPlace> S1 = new HashSet<>();
		S1.add(s3pr.getPlace("p6")); S1.add(s3pr.getPlace("p10")); S1.add(s3pr.getPlace("p11"));
		S1.add(s3pr.getPlace("p15")); S1.add(s3pr.getPlace("p16")); S1.add(s3pr.getPlace("p17"));
		S1.add(s3pr.getPlace("p18")); S1.add(s3pr.getPlace("p19"));
		
		Collection<PTPlace> S2 = new HashSet<>();
		S2.add(s3pr.getPlace("p4")); S2.add(s3pr.getPlace("p20")); S2.add(s3pr.getPlace("p21"));
		
		Collection<PTPlace> S3 = new HashSet<>();
		S3.add(s3pr.getPlace("p6")); S3.add(s3pr.getPlace("p9")); S3.add(s3pr.getPlace("p10"));
		S3.add(s3pr.getPlace("p15")); S3.add(s3pr.getPlace("p16")); S3.add(s3pr.getPlace("p17"));
		S3.add(s3pr.getPlace("p19"));
		
		Collection<PTPlace> S4 = new HashSet<>();
		S4.add(s3pr.getPlace("p5")); S4.add(s3pr.getPlace("p9")); S4.add(s3pr.getPlace("p15"));
		S4.add(s3pr.getPlace("p16")); S4.add(s3pr.getPlace("p17")); 
		
		Collection<PTPlace> S5 = new HashSet<>();
		S5.add(s3pr.getPlace("p6")); S5.add(s3pr.getPlace("p8")); S5.add(s3pr.getPlace("p10"));
		S5.add(s3pr.getPlace("p15")); S5.add(s3pr.getPlace("p16")); S5.add(s3pr.getPlace("p19"));
		
		// 信标的补集, 信标补集[S] = Hr(SR) \ S
		// SR: 信标S中的资源库所集合
		Collection<PTPlace> SR = new HashSet<>();
		SR.addAll(s3pr.getPR());
		SR.retainAll(S1);
		s3pr.printPNNodes("SR1 = ", SR);
		Collection<PTPlace> Scom1 = new HashSet<>();
		Scom1.addAll(s3pr.getHr(SR));
		Scom1.removeAll(S1);
		s3pr.printPNNodes("[S1] = ", Scom1);
		
		Collection<PTPlace> Scom2 = new HashSet<>();
		SR.clear();
		SR.addAll(s3pr.getPR());
		SR.retainAll(S2);
		s3pr.printPNNodes("SR2 = ", SR);
		Scom2.addAll(s3pr.getHr(SR));
		Scom2.removeAll(S2);
		s3pr.printPNNodes("[S2] = ", Scom2);
		
		Collection<PTPlace> Scom3 = new HashSet<>();
		SR.clear();
		SR.addAll(s3pr.getPR());
		SR.retainAll(S3);
		s3pr.printPNNodes("SR3 = ", SR);
		Scom3.addAll(s3pr.getHr(SR));
		Scom3.removeAll(S3);
		s3pr.printPNNodes("[S3] = ", Scom3);
		
		Collection<PTPlace> Scom4 = new HashSet<>();
		SR.clear();
		SR.addAll(s3pr.getPR());
		SR.retainAll(S4);
		s3pr.printPNNodes("SR4 = ", SR);
		Scom4.addAll(s3pr.getHr(SR));
		Scom4.removeAll(S4);
		s3pr.printPNNodes("[S4] = ", Scom4);
		
		Collection<PTPlace> Scom5 = new HashSet<>();
		SR.clear();
		SR.addAll(s3pr.getPR());
		SR.retainAll(S5);
		s3pr.printPNNodes("SR5 = ", SR);
		Scom5.addAll(s3pr.getHr(SR));
		Scom5.removeAll(S5);
		s3pr.printPNNodes("[S5] = ", Scom5);
		
		Collection<PTPlace> Scom31 = new HashSet<>();
		Scom31.addAll(Scom1);
		Scom31.retainAll(s3pr.getS2pr("s2pr_1").getPA());
		s3pr.printPNNodes("S31", Scom31);
		
	}
}