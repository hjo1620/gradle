/*
 * Copyright 2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.gradle.api.internal.artifacts.repositories.transport.http;

import org.apache.ivy.plugins.repository.Resource;
import org.gradle.api.internal.artifacts.ivyservice.filestore.CachedArtifact;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

class CachedResource implements Resource {
    private String source;
    private final File cacheFile;

    public CachedResource(String source, CachedArtifact cachedArtifact) {
        this.source = source;
        this.cacheFile = cachedArtifact.getOrigin();
    }

    @Override
    public String toString() {
        return "CachedResource: " + cacheFile + " for " + source;
    }

    public String getName() {
        return source;
    }

    public long getLastModified() {
        return cacheFile.lastModified();
    }

    public long getContentLength() {
        return cacheFile.length();
    }

    public boolean exists() {
        return true;
    }

    public boolean isLocal() {
        return true;
    }

    public Resource clone(String cloneName) {
        throw new UnsupportedOperationException();
    }

    public InputStream openStream() throws IOException {
        return new FileInputStream(cacheFile);
    }
}