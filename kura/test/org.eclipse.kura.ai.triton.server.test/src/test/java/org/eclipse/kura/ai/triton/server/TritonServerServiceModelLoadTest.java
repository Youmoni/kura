package org.eclipse.kura.ai.triton.server;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Matchers.anyObject;

import java.util.Optional;

import org.eclipse.kura.KuraIOException;
import org.junit.Test;

import inference.GRPCInferenceServiceGrpc.GRPCInferenceServiceBlockingStub;

public class TritonServerServiceModelLoadTest {

    TritonServerServiceImpl tritonServer;
    GRPCInferenceServiceBlockingStub grpcStubMock;

    @Test
    public void shouldLoadModel() throws KuraIOException {
        givenTritonServerServiceImpl();

        whenLoadModel();

        thenModelIsLoaded();
    }

    private void givenTritonServerServiceImpl() {
        this.tritonServer = new TritonServerServiceImpl();
        this.grpcStubMock = mock(GRPCInferenceServiceBlockingStub.class);
        this.tritonServer.setGrpcStub(grpcStubMock);
    }

    private void whenLoadModel() throws KuraIOException {
        this.tritonServer.loadModel("myModel", Optional.empty());
    }

    private void thenModelIsLoaded() {
        verify(this.grpcStubMock).repositoryModelLoad(anyObject());
    }
}
