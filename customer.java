package ben_aharoni_amit_halaly;

public class customer {
	
	private String customer_name;
	private String mobile;
	
	 public customer(String name, String mobile) {
	        this.customer_name = name;
	        this.mobile = mobile;
	    }

	    public String getName() {
	        return customer_name;
	    }

	    public String getMobile() {
	        return mobile;
	    }

		@Override
		public String toString() {
			return  customer_name + " \nmobile: " + mobile;
		}
}
