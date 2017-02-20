package algo;
import java.util.List;

public interface StringSearch {

	public void precompute(List<String> data);
	public List<String> lookFor(String pattern);
	public String getName();
}
