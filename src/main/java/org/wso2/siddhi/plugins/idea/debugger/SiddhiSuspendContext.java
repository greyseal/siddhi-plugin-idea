/*
 *  Copyright (c) 2017, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.wso2.siddhi.plugins.idea.debugger;

import com.intellij.util.containers.ContainerUtil;
import com.intellij.xdebugger.frame.XExecutionStack;
import com.intellij.xdebugger.frame.XStackFrame;
import com.intellij.xdebugger.frame.XSuspendContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.wso2.siddhi.plugins.idea.debugger.dto.Frame;
import org.wso2.siddhi.plugins.idea.debugger.dto.Message;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a suspended state of a debug process.
 */
public class SiddhiSuspendContext extends XSuspendContext {

    private static final String DEFAULT_THREAD_ID = "01";
    @NotNull
    private final SiddhiExecutionStack myStack;

    public SiddhiSuspendContext(@NotNull SiddhiDebugProcess process, @NotNull Message message) {

        String fileName = message.getLocation().getFileName();
        String frameName = message.getQueryName();
        Frame myFrame = new Frame(frameName, fileName);
        myFrame.setQueryName(message.getQueryName());
        myFrame.setEventInfo(message.getEventInfo());
        myFrame.setLocation(message.getLocation());
        myFrame.setQueryState(message.getQueryState());
        List<Frame> framesList = new ArrayList<>();
        framesList.add(myFrame);

        myStack = new SiddhiExecutionStack(process, framesList);
    }

    @Nullable
    @Override
    public XExecutionStack getActiveExecutionStack() {
        return myStack;
    }

    @NotNull
    @Override
    public XExecutionStack[] getExecutionStacks() {
        return new XExecutionStack[]{myStack};
    }

    static class SiddhiExecutionStack extends XExecutionStack {

        private final String threadId;
        @NotNull
        private final SiddhiDebugProcess myProcess;
        @NotNull
        private final List<SiddhiStackFrame> myStack;

        public SiddhiExecutionStack(@NotNull SiddhiDebugProcess process, List<Frame> frames) {
            super("Thread #" + DEFAULT_THREAD_ID);
            this.threadId = DEFAULT_THREAD_ID;
            this.myProcess = process;
            this.myStack = ContainerUtil.newArrayListWithCapacity(frames.size());
            for (Frame frame : frames) {
                myStack.add(new SiddhiStackFrame(myProcess, frame));
            }
        }

        @Nullable
        @Override
        public XStackFrame getTopFrame() {
            return ContainerUtil.getFirstItem(myStack);
        }

        @Override
        public void computeStackFrames(int firstFrameIndex, @NotNull XStackFrameContainer container) {
            container.addStackFrames(myStack, true);
        }

        public String getThreadId() {
            return threadId;
        }
    }
}
