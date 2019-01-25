package es.urjc.code.daw.tablonanuncios;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.TransferManagerBuilder;

@Controller
public class TablonController {

	@Autowired
	private AnunciosRepository repository;
	
	@Autowired
	private AmazonS3 s3;
	
	@Value("${cloud.aws.s3.bucket}")
	private String bucket;

	@PostConstruct
	public void init() {
		repository.save(new Anuncio("Pepe", "Hola caracola", "A description"));
		repository.save(new Anuncio("Juan", "Hola caracola", "A description"));
	}

	@RequestMapping("/")
	public String tablon(Model model, Pageable page) {

		model.addAttribute("anuncios", repository.findAll(page));

		return "tablon";
	}

	@RequestMapping("/anuncio/nuevo")
	public String nuevoAnuncio(Model model, 
			@RequestParam String nombre,
			@RequestParam String asunto,
			@RequestParam String comentario,
			@RequestParam String filename,
			@RequestParam MultipartFile file) {

        if (!file.isEmpty()) {
            try {
                ObjectMetadata objectMetadata = new ObjectMetadata();
                objectMetadata.setContentType(file.getContentType());

                TransferManager transferManager = TransferManagerBuilder.defaultTransferManager();
                transferManager.upload(bucket, filename, file.getInputStream(), objectMetadata);
                
            } catch (Exception e) {
            	model.addAttribute("message", "You failed to upload " + filename + " => " + e.getMessage());
                return "error";
            }
        } else {
        	model.addAttribute("message", "You failed to upload " + filename + " because the file was empty.");
            return "error";
        }

        Anuncio anuncio = new Anuncio(nombre, asunto, comentario);
        anuncio.setFoto(s3.getUrl(bucket, filename));

		repository.save(anuncio);

        return "anuncio_guardado";

	}

	@RequestMapping("/anuncio/{id}")
	public String verAnuncio(Model model, @PathVariable long id) {
		
		Anuncio anuncio = repository.findById(id).get();

		model.addAttribute("hasFoto", anuncio.getFoto() != null);
		model.addAttribute("anuncio", anuncio);

		return "ver_anuncio";
	}
	
}