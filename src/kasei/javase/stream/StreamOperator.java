import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamOperator {

    /** todo 流中间操作 */
    public void intermidateOperation(){
    
        // 无状态操作，每一个流元素都是独立处理，无相互之间的关系
        map
        mapToXxx
        flatMap
        flatMapToXxx
        filter
        peek
        unordered

        // 有状态操作，流元素处理会相互影响
        distinct
        sorted
        limit
        skip
    }
    
    /** todo 流终止操作 */
    public void terminationOperation(){
    
        // 短路操作：指如果退出，那就不继续处理后续流元素
        findFirst
        findAny
        allMatch
        anyMatch
        noneMatch
        
        // 非短路操作：对所有流元素处理
        forEach
        forEachOrdered
        collect
        toArray
        reduce
        min
        max
        count
        
    }
    
    /** todo 流并行操作 */
    public void parallelOperation(){
        Stream stream = null;
        stream.parallel().peek().sequential().peek();   // 先并行，再串行
    }    
    
    /** todo 流收集器操作 */
    public void collectorOperation(){
        List<Object> list = null;
        List<String> strList = list.stream().map(s->s.toString()).collect(Collectors.toList());
    
    }
    
    /** TODO 自定义流收集器 */
    public void customizedCollector(){
        String[] strs = {"1213", "sdf", "sda"};
        Stream<String> stringStream = Arrays.stream(strs);
        
        
        // 提供保存流收集操作结果的实例
        Supplier<Vector<String>> supplier = Vector::new;
        // 指定怎么收集单个流内容
        BiConsumer<Vector<String>, String> accumulator = (intermediate, input) -> intermediate.add(input);
        // 指定怎么收集并行操作时的流内容
        BinaryOperator<Vector<String>> combiner = (intermediate1, intermediate2) -> {intermediate1.addAll(intermediate2); return intermediate1;};
        // 将最终的结果转换成想要的对象
        Function<Vector<String>, String[]> finisher = intermediate -> intermediate.toArray(new String[intermediate.size()]);
        // 使用 Collector.of() 构建自定义流收集器
        Collector<String, Vector<String>, String[]> collector  = Collector.of(supplier, accumulator, combiner, finisher);
        
        
        String[] stt = stringStream.collect(collector);
        System.out.println(Arrays.toString(stt));
    }
    
}



