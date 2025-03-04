package xueluoanping.dtnatures_spirit.services;

import com.google.gson.*;
import cpw.mods.modlauncher.api.*;
import net.neoforged.fml.loading.FMLLoader;
import net.neoforged.fml.loading.moddiscovery.ModFileInfo;
import org.jetbrains.annotations.NotNull;
import xueluoanping.dtnatures_spirit.DTNaturesSpirit;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public class ServiceProvider implements ITransformationService {
    @Override
    public @NotNull String name() {
        return DTNaturesSpirit.MOD_ID + "_service";
    }

    @Override
    public void initialize(IEnvironment environment) {

    }

    @Override
    public void onLoad(IEnvironment env, Set<String> otherServices) {

    }

    @Override
    public @NotNull List<? extends ITransformer<?>> transformers() {
        return List.of();
    }

    @Override
    public List<Resource> completeScan(IModuleLayerManager layerManager) {
        if (FMLLoader.getLoadingModList().getModFileById("natures_spirit") instanceof ModFileInfo modFileInfo) {
            // try {
            //     modifyFabricModJsonInJar(modFileInfo.getFile().getFilePath().toString());
            // } catch (IOException e) {
            //     e.printStackTrace();
            // }
        }
        return ITransformationService.super.completeScan(layerManager);
    }

    public static void modifyFabricModJsonInJar(String jarFilePath) throws IOException {
        File inputJar = new File(jarFilePath);
        File tempJar = new File(jarFilePath + "_2");

        try (ZipFile zipFile = new ZipFile(inputJar);
             ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(tempJar))) {

            boolean modified = false;

            // 遍历原 JAR 文件
            Enumeration<? extends ZipEntry> entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();
                InputStream is = zipFile.getInputStream(entry);

                // 修改 fabric.mod.json
                if (entry.getName().equals("fabric.mod.json")) {
                    System.out.println("Modifying fabric.mod.json...");
                    // 读取 JSON
                    String jsonContent = new String(is.readAllBytes(), StandardCharsets.UTF_8);
                    JsonObject json = JsonParser.parseString(jsonContent).getAsJsonObject();

                    JsonArray jars = json.getAsJsonArray("jars");
                    JsonArray nJars = new JsonArray();
                    for (JsonElement jar : jars) {
                        String file = jar.getAsJsonObject().get("file").getAsString();
                        if (file.equals("META-INF/jars/core-3.6.7.jar") || file.equals("META-INF/jars/toml-3.6.7.jar"))
                            continue;
                        nJars.add(jar);
                    }
                    json.add("jars", nJars);
                    // 写入修改后的 JSON
                    byte[] modifiedJsonBytes = new Gson().toJson(json).getBytes(StandardCharsets.UTF_8);
                    zos.putNextEntry(new ZipEntry("fabric.mod.json"));
                    zos.write(modifiedJsonBytes);
                    zos.closeEntry();
                    modified = true;
                } else {
                    // 复制其他文件
                    zos.putNextEntry(new ZipEntry(entry.getName()));
                    is.transferTo(zos);
                    zos.closeEntry();
                }
                is.close();
            }

            if (modified) {
                System.out.println("Modification complete! Saving as: " + inputJar.getAbsolutePath());
            } else {
                System.err.println("fabric.mod.json not found!");
            }
        }

        // 将临时 JAR 文件重命名为原始文件，覆盖原文件
        if (inputJar.delete()&&tempJar.renameTo(inputJar)) {
            System.out.println("Original JAR replaced successfully!");
        } else {
            System.err.println("Failed to replace the original JAR file.");
        }
    }

}
