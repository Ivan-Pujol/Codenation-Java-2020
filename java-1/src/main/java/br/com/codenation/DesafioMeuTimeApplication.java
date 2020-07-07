package br.com.codenation;

import br.com.codenation.exceptions.CapitaoNaoInformadoException;
import br.com.codenation.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.exceptions.JogadorNaoEncontradoException;
import br.com.codenation.exceptions.TimeNaoEncontradoException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


public class DesafioMeuTimeApplication implements MeuTimeInterface {
	List<Jogador> players = new ArrayList<>();
	List<Time> teams = new ArrayList<>();

	public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) {
		List<Long> newTeams = buscarTimes();
		if (teams.size()>0){
			for(Time time: teams){
				if (time.getId() == id){
					throw new IdentificadorUtilizadoException("Team Id already taken, please check the ID.");
				}
			}
		}
		teams.add(new Time(id, nome, dataCriacao, corUniformePrincipal, corUniformeSecundario));
	}

	public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) {
		//throw new UnsupportedOperationException();
		if (teams.size() < 1){
			throw new TimeNaoEncontradoException("None team was found.");
		}
		List<Long> playersOfTeam =  buscarJogadoresDoTime(idTime);
		if (playersOfTeam != null){
			for(Jogador jogador: players){
				if (jogador.getId() == id){
					throw new IdentificadorUtilizadoException("Player is already registered.");
				}
			}
		}
		players.add(new Jogador(id, idTime, nome, dataNascimento, nivelHabilidade, salario));


	}
	public List<Long> buscarTimes() {
		List<Long>teams1 = new ArrayList<>();
		for(Time time:teams){
			teams1.add(time.getId());
		}
		return teams1;
	}

	public List<Long> buscarJogadoresDoTime(Long idTime) {
		//throw new UnsupportedOperationException();
		List<Long> playersOfTeam = new ArrayList<>();
		if (teams.size()>0){
			for(Time time:teams){
				if (time.getId() == idTime){
					for (Jogador jogador: players){
						if (jogador.getIdTime() == idTime){
							playersOfTeam.add(jogador.getId());
						}
					}
				}
			}
			return playersOfTeam;
		}
		throw new TimeNaoEncontradoException("None team was found.");
	}


	public void definirCapitao(Long idJogador) {
		boolean isPlayer= false;
		boolean teamExist = false;
		Long idTime = 0L;
		if (players.size()>0) {
			for (Jogador jogador : players) {
				if (jogador.getId() == idJogador) {
					isPlayer = true;
					idTime = jogador.getIdTime();
					break;
				}
			}
		}else{
			throw new JogadorNaoEncontradoException("Player not found.");
		}
		if (teams.size()>0) {
			for (Time time : teams) {
				if (time.getId() == idTime) {
					teamExist = true;
					break;
				}
			}
		}else{
			throw new TimeNaoEncontradoException("Team not found.");
		}
		if (isPlayer==true && teamExist==true){
			for (Jogador jogador : players) {
				if (jogador.getId() == idJogador) {
					jogador.setCapitao(true);
					break;
				}
			}
		}
		if (isPlayer==false){
			throw new JogadorNaoEncontradoException("Player not found.");
		}
		if (teamExist==false){
			throw new TimeNaoEncontradoException("Team not found.");
		}
	}

	public Long buscarCapitaoDoTime(Long idTime) {
		Long hasCaptain = null;
		boolean teamExist = false;
		if (teams.size()>0){
			for (Time time:teams) {
				if (time.getId()==idTime){
					teamExist=true;
					break;
				}
			}
			if (teamExist=false){
				throw new TimeNaoEncontradoException("Team does not exist.");
			}
		}else{
			throw new TimeNaoEncontradoException("Team does not exist.");
		}

		for (Jogador jogador: players){
			if (jogador.getIdTime() == idTime){
				if (jogador.isCapitao()){
					hasCaptain = jogador.getId();
					return hasCaptain;
				}
			}
		}
		if (hasCaptain == null){
			throw new CapitaoNaoInformadoException("No captain was found.");
		}
		return hasCaptain;
	}

	public String buscarNomeJogador(Long idJogador) {
		//throw new UnsupportedOperationException();
		if (players.size()>0){
			for (Jogador jogador:players) {
				if (jogador.getId() == idJogador){
					String nome = jogador.getNome();
					return nome;
				}
			}
		}
		throw new JogadorNaoEncontradoException("No player was found.");
	}

	public String buscarNomeTime(Long idTime) {
		//throw new UnsupportedOperationException();
		if (teams.size()>0){
			for (Time time:teams) {
				if (time.getId() == idTime){
					String nome = time.getNome();
					return nome;
				}
			}
		}
		throw new TimeNaoEncontradoException("No team was found.");
	}



	public Long buscarMelhorJogadorDoTime(Long idTime) {
		//throw new UnsupportedOperationException();
		Long skilledPlayer = 0L;
		int abilityLevel = 0;
		if (players.size()>0){
			for (Jogador jogador:players){
				if (jogador.getIdTime() == idTime){
					if (jogador.getNivelHabilidade() > abilityLevel){
						abilityLevel = jogador.getNivelHabilidade();
						skilledPlayer = jogador.getId();
					}
				}
			}
			return skilledPlayer;
		}
		throw new TimeNaoEncontradoException("Wasn't found any team.");
	}

	public Long buscarJogadorMaisVelho(Long idTime) {
		//throw new UnsupportedOperationException();
		LocalDate date = LocalDate.now();
		Long idPlayer = 0L;
		if (players.size()>0){
			for (Jogador jogador:players){
				if (jogador.getIdTime() == idTime){
					if (jogador.getDataNascimento().isBefore(date)){
						date = jogador.getDataNascimento();
						idPlayer = jogador.getId();
					}
				}
			}
			return idPlayer;
		}
		throw new TimeNaoEncontradoException("Team was not found.");
	}



	public Long buscarJogadorMaiorSalario(Long idTime) {
		//throw new UnsupportedOperationException();
		BigDecimal money = BigDecimal.valueOf(1);
		Long playerId = 0L;
		if (players.size()>0){
			for (Jogador jogador:players){
				if (jogador.getIdTime() == idTime){
					if (jogador.getSalario().intValue() > money.intValue()){
						money = jogador.getSalario();
						playerId = jogador.getId();
					}
				}
			}
			return playerId;
		}
		throw new TimeNaoEncontradoException("Team was not found.");
	}

	public BigDecimal buscarSalarioDoJogador(Long idJogador) {
		//throw new UnsupportedOperationException();
		BigDecimal money = BigDecimal.valueOf(0);
		Long playerId = 0L;
		if (players.size()>0){
			for (Jogador jogador:players){
				if (jogador.getId() == idJogador){
					money = jogador.getSalario();
				}
			}
			return money;
		}
		throw new JogadorNaoEncontradoException("No player was found.");
	}

	public List<Long> buscarTopJogadores(Integer top) {
		//throw new UnsupportedOperationException();
		return players.stream().sorted(Comparator.comparingInt(Jogador::getNivelHabilidade).reversed().thenComparingLong(Jogador::getId)).limit(top).mapToLong(Jogador::getId).boxed().collect(Collectors.toList());
	}

}
