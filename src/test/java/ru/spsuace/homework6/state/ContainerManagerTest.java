package ru.spsuace.homework6.state;

import org.junit.Test;

import static org.junit.Assert.*;

public class ContainerManagerTest {

    @Test
    public void oneContainer() throws Exception {
        ContainerManager manager = new ContainerManager(1);
        assertEquals(State.START, manager.getCalculateContainers().get(0).getState());

        manager.initContainers();
        assertFalse(manager.await(100));
        assertEquals(State.INIT, manager.getCalculateContainers().get(0).getState());

        manager.runContainers();
        assertFalse(manager.await(100));
        assertEquals(State.RUN, manager.getCalculateContainers().get(0).getState());

        manager.finishContainers();
        assertFalse(manager.await(100));
        assertEquals(State.FINISH, manager.getCalculateContainers().get(0).getState());

        manager.closeContainers();
        assertTrue(manager.await(100));
        assertEquals(State.CLOSE, manager.getCalculateContainers().get(0).getState());
    }


    @Test
    public void oneManyInitContainer() throws Exception {
        ContainerManager manager = new ContainerManager(1);
        assertEquals(State.START, manager.getCalculateContainers().get(0).getState());

        manager.initContainers();
        assertFalse(manager.await(100));
        assertEquals(State.INIT, manager.getCalculateContainers().get(0).getState());

        manager.runContainers();
        assertFalse(manager.await(100));
        assertEquals(State.RUN, manager.getCalculateContainers().get(0).getState());

        manager.finishContainers();
        assertFalse(manager.await(100));
        assertEquals(State.FINISH, manager.getCalculateContainers().get(0).getState());

        manager.initContainers();
        assertFalse(manager.await(100));
        assertEquals(State.INIT, manager.getCalculateContainers().get(0).getState());

        manager.runContainers();
        assertFalse(manager.await(100));
        assertEquals(State.RUN, manager.getCalculateContainers().get(0).getState());

        manager.finishContainers();
        assertFalse(manager.await(100));
        assertEquals(State.FINISH, manager.getCalculateContainers().get(0).getState());

        manager.closeContainers();
        assertTrue(manager.await(100));
        assertEquals(State.CLOSE, manager.getCalculateContainers().get(0).getState());
    }


    @Test
    public void oneNotCurrentContainer() throws Exception {
        ContainerManager manager = new ContainerManager(1);
        assertEquals(State.START, manager.getCalculateContainers().get(0).getState());

        manager.initContainers();
        assertFalse(manager.await(100));
        assertEquals(State.INIT, manager.getCalculateContainers().get(0).getState());

        manager.finishContainers();
        assertFalse(manager.await(100));
        assertEquals(State.INIT, manager.getCalculateContainers().get(0).getState());

        manager.runContainers();
        assertFalse(manager.await(500));
        assertEquals(State.FINISH, manager.getCalculateContainers().get(0).getState());

        manager.closeContainers();
        assertTrue(manager.await(100));
        assertEquals(State.CLOSE, manager.getCalculateContainers().get(0).getState());
    }

}