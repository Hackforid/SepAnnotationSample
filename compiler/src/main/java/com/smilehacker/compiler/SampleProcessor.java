package com.smilehacker.compiler;

import java.io.IOException;
import java.io.Writer;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import javax.tools.FileObject;
import javax.tools.StandardLocation;

import com.smilehacker.annotation.SampleAnnotation;

public class SampleProcessor extends AbstractProcessor {

  public static final String DEFAULT_FILE_NAME = "META-INF/services/inject/invoker_info";

  private Filer mFiler;
  private boolean mWritten = false;

  @Override
  public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
    mFiler = processingEnv.getFiler();
    writeSampleResource();
    return false;
  }

  @Override
  public Set<String> getSupportedAnnotationTypes() {
    Set<String> annotations = new LinkedHashSet<>();
    annotations.add(SampleAnnotation.class.getCanonicalName());
    return annotations;
  }

  @Override
  public SourceVersion getSupportedSourceVersion() {
    return SourceVersion.RELEASE_7;
  }

  private void writeSampleResource() {
    if (mWritten) {
      return;
    }
    mWritten = true;

    Writer writer = null;
    try {
      FileObject source = mFiler.createResource(StandardLocation.CLASS_OUTPUT, "", DEFAULT_FILE_NAME);
      writer = source.openWriter();

      writer.append("HelloWorld");
      writer.flush();

    } catch (IOException e) {
      e.printStackTrace();
      if (writer != null) {
        try {
          writer.close();
        } catch (IOException ex) {
          ex.printStackTrace();
        }
      }
    }
  }
}
