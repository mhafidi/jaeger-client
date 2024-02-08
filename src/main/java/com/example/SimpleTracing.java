package com.example;
import io.jaegertracing.Configuration;
import io.jaegertracing.internal.JaegerTracer;
import io.jaegertracing.internal.reporters.InMemoryReporter;
import io.jaegertracing.internal.samplers.ConstSampler;
import io.opentracing.Span;
import io.opentracing.Tracer;

public class SimpleTracing {

    private final Tracer tracer;

    private SimpleTracing(Tracer tracer) {
        this.tracer = tracer;
    }

    private void sayHello(String helloTo) {
        Span span = tracer.buildSpan("say-hello").start();
        span.setTag("hello-to", helloTo);

        String helloStr = String.format("Hello, %s!", helloTo);
        span.log(helloStr);

        span.finish();
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            throw new IllegalArgumentException("Expecting one argument");
        }

        String helloTo = args[0];

        Configuration.SamplerConfiguration samplerConfig = Configuration.SamplerConfiguration.fromEnv().withType(ConstSampler.TYPE).withParam(1);
        InMemoryReporter reporter = new InMemoryReporter();
        ConstSampler sampler = new ConstSampler(true);

        JaegerTracer tracer = new JaegerTracer.Builder("hello-world")
            .withReporter(reporter)
            .withSampler(sampler)
            .build();

        SimpleTracing hello = new SimpleTracing(tracer);
        hello.sayHello(helloTo);
    }
}

