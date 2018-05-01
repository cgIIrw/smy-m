import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Name;
import javax.lang.model.util.ElementScanner6;
import javax.tools.Diagnostic;

/**
 * Created by yin on 18/5/1.
 */
public class MethodNameGetter {
    private final Messager messager;
    NamegetterScanner namegetterScanner = new NamegetterScanner();

    MethodNameGetter(ProcessingEnvironment processingEnvironment) {
        this.messager = processingEnvironment.getMessager();
    }

    public void getNames(Element element) {
        namegetterScanner.scan(element);
    }

    private class NamegetterScanner extends ElementScanner6<Void, Void> {

        @Override
        public Void visitExecutable(ExecutableElement e, Void p) {
            if (e.getKind() == ElementKind.METHOD || e.getKind() == ElementKind.CONSTRUCTOR) {
                Name name = e.getSimpleName();
                messager.printMessage(Diagnostic.Kind.NOTE, e.toString());
            }

            super.visitExecutable(e, p);
            return null;
        }
    }
}
