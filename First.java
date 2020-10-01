import java.util.*;

class First 
{ 

	static Scanner sc =new Scanner(System.in);    
    static double INF = 10000; 
    static class Pt { 
        double x; 
        double y; 
        public Pt(double x, double y) { 
            this.x = x; 
            this.y = y; 
        } 
    }; 

    static Pt[] input(){

		String s= sc.nextLine();

		s= s.replace("[","");
		s= s.replace("]","");
		double[] arr=Arrays.stream(s.split(",")).mapToDouble(i->(double)Double.parseDouble(i)).toArray();
		ArrayList<Pt> p=new ArrayList<>();
		for(int i=0;i<arr.length;i+=2){
			p.add(new Pt(arr[i],arr[i+1]));

		}
		Pt[] k =new Pt[p.size()];
		for(int i=0;i<p.size();i++){
			k[i]=p.get(i);
			
		}
		return k;
	}
  


    static boolean onLine(Pt p, Pt q, Pt r)  { 

        if (q.x <= Math.max(p.x, r.x) && q.x >= Math.min(p.x, r.x) &&q.y <= Math.max(p.y, r.y) &&q.y >= Math.min(p.y, r.y)) { 
            return true; 
        } 
        return false; 
    } 

    static int orientation(Pt p, Pt q, Pt r)   { 
        double val = (q.y - p.y) * (r.x - q.x) - (q.x - p.x) * (r.y - q.y); 
        if (val == 0) 
            return 0;  
        return (val > 0) ? 1 : 2;  
    } 
    static boolean isLineIntersect(Pt p1, Pt q1, Pt p2, Pt q2)  { 
        int o1 = orientation(p1, q1, p2); 
        int o2 = orientation(p1, q1, q2); 
        int o3 = orientation(p2, q2, p1); 
        int o4 = orientation(p2, q2, q1); 
        if (o1 != o2 && o3 != o4)    { 
            return true; 
        }    
        if (o1 == 0 && onLine(p1, p2, q1))  
        { 
            return true; 
        } 
        if (o2 == 0 && onLine(p1, q2, q1))  
        { 
            return true; 
        } 
        if (o3 == 0 && onLine(p2, p1, q2)) 
        { 
            return true; 
        } 
        if (o4 == 0 && onLine(p2, q1, q2)) 
        { 
            return true; 
        } 
        return false;  

    } 

    static boolean isInsidePolygon(Pt polygon[], int n, Pt p)     { 
        if (n < 3) { 
            return false; 
        } 
        Pt extreme = new Pt(INF, p.y); 
        int count = 0, i = 0; 
        do { 
            int next = (i + 1) % n; 

            if (isLineIntersect(polygon[i], polygon[next], p, extreme))        { 
                if (orientation(polygon[i], p, polygon[next]) == 0) { 
                    return onLine(polygon[i], p, polygon[next]); 
                } 
                count++; 
            } 
            i = next; 
        } while (i != 0); 

        return (count % 2 == 1); 

    } 

    public static void main(String[] args)  

    { 

        Pt polygon[] = input();
	int n = polygon.length; 
	String k=sc.nextLine();
	k=k.replace("[","");
	k=k.replace("]","");
	double x=Double.parseDouble(k.split(",")[0]);
	double y=Double.parseDouble(k.split(",")[1]);
        Pt p = new Pt(x, y); 
        if (isInsidePolygon(polygon, n, p)) { 
            System.out.println("True"); 
        }  
        else 
        { 
            System.out.println("False"); 
        } 

    } 
} 

  
 
