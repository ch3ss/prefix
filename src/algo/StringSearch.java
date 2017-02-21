package algo;
import java.util.List;

public interface StringSearch {

	public void precompute(List<String> data);
	public List<String> search(String pattern);
	public String getName();
}
