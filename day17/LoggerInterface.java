import java.util.*;

interface LoggerInterface {

	Logger logger = new Logger();

	default void log(String msg) {
		logger.log(msg);
	}

}
