package org.boliveira.drawing.control;

import lombok.extern.log4j.Log4j;
import org.apache.commons.beanutils.ConvertUtils;
import org.boliveira.drawing.domain.Canvas;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


/**
 * Command orchestrator for a canvas operations
 */
@Log4j
public class CanvasCommandOrchestrator implements Orchestrable<Canvas> {

    private final Commandable canvasDrawer;

    public CanvasCommandOrchestrator() {
        this.canvasDrawer = new CanvasDrawer();
    }

    /**
     * Orchestrates a command according to the first character of the command line.
     * See {@link Commandable } for list of commands handled by the application.
     *
     * @param commandLine command line to be executed
     */
    @Override
    public Canvas execute(String commandLine) {
        var values = commandLine.split(" ");
        try {
            return this.invokeByCommand(values);
        } catch (CommandNotFoundException e) {
            throw e;
        } catch (InvocationTargetException e) {
            throw new CommandExecutionFailedException(e.getCause().getMessage(), e);
        } catch (Exception e) {
            throw new CommandExecutionFailedException(e.getMessage(), e);
        }
    }

    /**
     * Resolves the Command method reflexively, parsing the args and passing on to the method.
     *
     * @param args command line
     * @return true if the method was invoked successfully, false if command was not found.
     * @throws ReflectiveOperationException if invocation in the target went wrong or method illegal access
     */
    private Canvas invokeByCommand(String... args) throws ReflectiveOperationException {
        var command = args[0];
        var parameters = new Object[args.length - 1];
        var commandMethod = Commandable.class.getDeclaredMethods();
        for (Method method : commandMethod) {
            if (method.isAnnotationPresent(Commandable.Command.class)) {
                Commandable.Command commandAnnotation = method.getDeclaredAnnotation(Commandable.Command.class);
                if (commandAnnotation.value().equals(command)) {
                    Class[] parameterTypes = method.getParameterTypes();
                    for (int i = 0; i < parameterTypes.length; i++) {
                        parameters[i] = ConvertUtils.convert(args[i + 1], parameterTypes[i]);
                    }
                    return (Canvas) method.invoke(this.canvasDrawer, parameters);
                }
            }
        }
        throw new CommandNotFoundException("Command not found");
    }
}
