package com.test.finder;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Load customers.json file from resources folder.
 *
 * @author Cassio Dias
 */
public class CustomerResource {

    public File loadFile() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        final URL resource = classLoader.getResource("customers.json");

        if (resource == null) return null;

        return new File(resource.getFile());
    }

}
